package com.vaadin.demo.sampler.features.dragndrop;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.demo.sampler.ExampleUtil;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.And;
import com.vaadin.event.dd.acceptcriteria.ClientSideCriterion;
import com.vaadin.event.dd.acceptcriteria.SourceIs;
import com.vaadin.ui.AbstractSelect.AbstractSelectTargetDetails;
import com.vaadin.ui.AbstractSelect.AcceptItem;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TargetItemAllowsChildren;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.Window.Notification;

/**
 * Demonstrate moving data back and forth between a table and a tree using drag
 * and drop.
 * 
 * The tree and the table use different data structures: The category is a
 * separate node in the tree and each item just has a String, whereas the table
 * contains items with both a name and a category. Data conversions between
 * these representations are made during drop processing.
 */
public class DragDropTableTreeExample extends HorizontalLayout {

    private Tree tree;
    private Table table;

    public static class Hardware implements Serializable {
        private String name;
        private String category;

        public Hardware(String name, String category) {
            this.name = name;
            this.category = category;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

    public DragDropTableTreeExample() {
        setSpacing(true);

        // First create the components to be able to refer to them as allowed
        // drag sources
        tree = new Tree("Drag from tree to table");
        table = new Table("Drag from table to tree");
        table.setWidth("100%");

        // Populate the tree and set up drag & drop
        initializeTree(new SourceIs(table));

        // Populate the table and set up drag & drop
        initializeTable(new SourceIs(tree));

        // Add components
        addComponent(tree);
        addComponent(table);
    }

    private void initializeTree(final ClientSideCriterion acceptCriterion) {
        tree.setContainerDataSource(ExampleUtil.getHardwareContainer());
        tree.setItemCaptionPropertyId(ExampleUtil.hw_PROPERTY_NAME);

        // Expand all nodes
        for (Iterator<?> it = tree.rootItemIds().iterator(); it.hasNext();) {
            tree.expandItemsRecursively(it.next());
        }
        tree.setDragMode(TreeDragMode.NODE);
        tree.setDropHandler(new DropHandler() {
            public void drop(DragAndDropEvent dropEvent) {
                // criteria verify that this is safe
                DataBoundTransferable t = (DataBoundTransferable) dropEvent
                        .getTransferable();
                Container sourceContainer = t.getSourceContainer();
                Object sourceItemId = t.getItemId();
                Item sourceItem = sourceContainer.getItem(sourceItemId);
                String name = sourceItem.getItemProperty("name").toString();
                String category = sourceItem.getItemProperty("category")
                        .toString();

                AbstractSelectTargetDetails dropData = ((AbstractSelectTargetDetails) dropEvent
                        .getTargetDetails());
                Object targetItemId = dropData.getItemIdOver();

                // find category in target: the target node itself or its parent
                if (targetItemId != null && name != null && category != null) {
                    String treeCategory = getTreeNodeName(tree, targetItemId);
                    if (category.equals(treeCategory)) {
                        // move item from table to category'
                        Object newItemId = tree.addItem();
                        tree.getItem(newItemId)
                                .getItemProperty(ExampleUtil.hw_PROPERTY_NAME)
                                .setValue(name);
                        tree.setParent(newItemId, targetItemId);
                        tree.setChildrenAllowed(newItemId, false);

                        sourceContainer.removeItem(sourceItemId);
                    } else {
                        String message = name
                                + " is not a "
                                + treeCategory.toLowerCase().replaceAll("s$",
                                        "");
                        getWindow().showNotification(message,
                                Notification.TYPE_WARNING_MESSAGE);
                    }
                }
            }

            public AcceptCriterion getAcceptCriterion() {
                // Only allow dropping of data bound transferables within
                // folders.
                // In this example, checking for the correct category in drop()
                // rather than in the criteria.
                return new And(acceptCriterion, TargetItemAllowsChildren.get(),
                        AcceptItem.ALL);
            }
        });
    }

    private void initializeTable(final ClientSideCriterion acceptCriterion) {
        final BeanItemContainer<Hardware> tableContainer = new BeanItemContainer<Hardware>(
                Hardware.class);
        tableContainer.addItem(new Hardware("Dell OptiPlex 380", "Desktops"));
        tableContainer.addItem(new Hardware("Benq T900HD", "Monitors"));
        tableContainer.addItem(new Hardware("Lenovo ThinkPad T500", "Laptops"));
        table.setContainerDataSource(tableContainer);
        table.setVisibleColumns(new Object[] { "category", "name" });

        // Handle drop in table: move hardware item or subtree to the table
        table.setDragMode(TableDragMode.ROW);
        table.setDropHandler(new DropHandler() {
            public void drop(DragAndDropEvent dropEvent) {
                // criteria verify that this is safe
                DataBoundTransferable t = (DataBoundTransferable) dropEvent
                        .getTransferable();
                if (!(t.getSourceContainer() instanceof Container.Hierarchical)) {
                    return;
                }
                Container.Hierarchical source = (Container.Hierarchical) t
                        .getSourceContainer();

                Object sourceItemId = t.getItemId();

                // find and convert the item(s) to move

                Object parentItemId = source.getParent(sourceItemId);
                // map from moved source item Id to the corresponding Hardware
                LinkedHashMap<Object, Hardware> hardwareMap = new LinkedHashMap<Object, Hardware>();
                if (parentItemId == null) {
                    // move the whole subtree
                    String category = getTreeNodeName(source, sourceItemId);
                    Collection<?> children = source.getChildren(sourceItemId);
                    if (children != null) {
                        for (Object childId : children) {
                            String name = getTreeNodeName(source, childId);
                            hardwareMap.put(childId, new Hardware(name,
                                    category));
                        }
                    }
                } else {
                    // move a single hardware item
                    String category = getTreeNodeName(source, parentItemId);
                    String name = getTreeNodeName(source, sourceItemId);
                    hardwareMap.put(sourceItemId, new Hardware(name, category));
                }

                // move item(s) to the correct location in the table

                AbstractSelectTargetDetails dropData = ((AbstractSelectTargetDetails) dropEvent
                        .getTargetDetails());
                Object targetItemId = dropData.getItemIdOver();

                for (Object sourceId : hardwareMap.keySet()) {
                    Hardware hardware = hardwareMap.get(sourceId);
                    if (targetItemId != null) {
                        switch (dropData.getDropLocation()) {
                        case BOTTOM:
                            tableContainer.addItemAfter(targetItemId, hardware);
                            break;
                        case MIDDLE:
                        case TOP:
                            Object prevItemId = tableContainer
                                    .prevItemId(targetItemId);
                            tableContainer.addItemAfter(prevItemId, hardware);
                            break;
                        }
                    } else {
                        tableContainer.addItem(hardware);
                    }
                    source.removeItem(sourceId);
                }
            }

            public AcceptCriterion getAcceptCriterion() {
                return new And(acceptCriterion, AcceptItem.ALL);
            }
        });
    }

    private static String getTreeNodeName(Container.Hierarchical source,
            Object sourceId) {
        return (String) source.getItem(sourceId)
                .getItemProperty(ExampleUtil.hw_PROPERTY_NAME).getValue();
    }

}
