package com.vaadin.demo.sampler.features.dragndrop;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.demo.sampler.ExampleUtil;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.ServerSideCriterion;
import com.vaadin.terminal.gwt.client.ui.dd.VerticalDropLocation;
import com.vaadin.ui.AbstractSelect.AbstractSelectTargetDetails;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.TableDragMode;

public class DragDropServerValidationExample extends HorizontalLayout {

    private Table table;
    private IndexedContainer container;

    private class RelativeCriterion extends ServerSideCriterion {
        public boolean accept(DragAndDropEvent dragEvent) {
            // only accept drags within the table
            if (dragEvent.getTransferable().getSourceComponent() != table
                    || !(dragEvent.getTransferable() instanceof DataBoundTransferable)) {
                return false;
            }

            // AbstractSelectDropTargetDetails as in a Table
            AbstractSelectTargetDetails dropData = (AbstractSelectTargetDetails) dragEvent
                    .getTargetDetails();
            // only allow drop over a row, not between rows
            if (!VerticalDropLocation.MIDDLE.equals(dropData.getDropLocation())) {
                return false;
            }

            DataBoundTransferable t = (DataBoundTransferable) dragEvent
                    .getTransferable();

            // check that two different persons whose last names match
            Object sourceItemId = t.getItemId();
            Object targetItemId = dropData.getItemIdOver();
            if (sourceItemId.equals(targetItemId)) {
                return false;
            }
            String sourceLastName = getLastName(sourceItemId);
            String targetLastName = getLastName(targetItemId);
            if (sourceLastName != null && sourceLastName.equals(targetLastName)) {
                return true;
            }
            return false;
        }
    };

    public DragDropServerValidationExample() {
        setSpacing(true);

        // First create the components to be able to refer to them as allowed
        // drag sources
        table = new Table("Drag persons onto their relatives");
        table.setWidth("100%");

        container = ExampleUtil.getPersonContainer();
        table.setContainerDataSource(container);

        // Drag and drop support
        table.setDragMode(TableDragMode.ROW);
        table.setDropHandler(new DropHandler() {
            public void drop(DragAndDropEvent dropEvent) {
                // criteria verify that this is safe
                DataBoundTransferable t = (DataBoundTransferable) dropEvent
                        .getTransferable();

                Object sourceItemId = t.getItemId();

                AbstractSelectTargetDetails dropData = ((AbstractSelectTargetDetails) dropEvent
                        .getTargetDetails());
                Object targetItemId = dropData.getItemIdOver();

                // tell that the persons are related
                getWindow().showNotification(
                        getFullName(sourceItemId) + " is related to "
                                + getFullName(targetItemId));
            }

            public AcceptCriterion getAcceptCriterion() {
                // during the drag and on drop, check that two different persons
                // with the same last name
                return new RelativeCriterion();
            }
        });

        addComponent(table);
    }

    private String getFullName(Object itemId) {
        Item item = container.getItem(itemId);
        if (item == null) {
            // should not happen in this example
            return null;
        }
        String fn = (String) item.getItemProperty(
                ExampleUtil.PERSON_PROPERTY_FIRSTNAME).getValue();
        String ln = (String) item.getItemProperty(
                ExampleUtil.PERSON_PROPERTY_LASTNAME).getValue();
        return fn + " " + ln;
    }

    private String getLastName(Object itemId) {
        Item item = container.getItem(itemId);
        if (item == null) {
            // should not happen in this example
            return null;
        }
        return (String) item.getItemProperty(
                ExampleUtil.PERSON_PROPERTY_LASTNAME).getValue();
    }

}
