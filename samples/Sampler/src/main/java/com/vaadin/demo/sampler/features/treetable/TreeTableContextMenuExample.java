package com.vaadin.demo.sampler.features.treetable;

import java.util.Date;

import com.vaadin.event.Action;

public class TreeTableContextMenuExample extends TreeTableBasicExample {

    private static final Action ADD_ITEM_ACTION = new Action("Add item");

    private static final Action ADD_CATEGORY_ACTION = new Action("Add category");

    private static final Action REMOVE_ITEM_ACTION = new Action("Remove");

    /**
     * This example example extends the TreeTableBasic example. The source code
     * of TreeTableBasic can be found as its own sample in the sampler
     */
    public TreeTableContextMenuExample() {
        super();
        
        /*
         * Add a context menu to the tree table.
         * 
         * treetable has been created in TreeTableBasic
         */
        treetable.addActionHandler(new Action.Handler() {

            public void handleAction(Action action, Object sender, Object target) {
                if (action == ADD_ITEM_ACTION) {
                    // Create new item
                    Object item = treetable.addItem(new Object[] { "New Item",
                            0, new Date() }, null);
                    treetable.setChildrenAllowed(item, false);
                    treetable.setParent(item, target);
                } else if (action == ADD_CATEGORY_ACTION) {
                    Object item = treetable.addItem(new Object[] {
                            "New Category", 0, new Date() }, null);
                    treetable.setParent(item, target);
                } else if (action == REMOVE_ITEM_ACTION) {
                    treetable.removeItem(target);
                }
            }

            public Action[] getActions(Object target, Object sender) {

                if (target == null) {
                    // Context menu in an empty space -> add a new main category
                    return new Action[]{ ADD_CATEGORY_ACTION };

                } else if (treetable.areChildrenAllowed(target)) {
                    // Context menu for a category
                    return new Action[]{ ADD_CATEGORY_ACTION, ADD_ITEM_ACTION, REMOVE_ITEM_ACTION };
                    
                } else {
                    // Context menu for an item
                    return new Action[] { REMOVE_ITEM_ACTION };
                }
            }
        });
    }
}
