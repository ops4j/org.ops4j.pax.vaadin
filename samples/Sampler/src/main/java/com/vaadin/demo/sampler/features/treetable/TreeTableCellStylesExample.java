package com.vaadin.demo.sampler.features.treetable;

import com.vaadin.ui.Table;

public class TreeTableCellStylesExample extends TreeTableBasicExample {

    /**
     * This example example extends the TreeTableBasic example. The source code
     * of TreeTableBasic can be found as its own sample in the sampler
     */
    public TreeTableCellStylesExample() {

        treetable.setStyleName("contacts");

        treetable.setCellStyleGenerator(new Table.CellStyleGenerator() {
            public String getStyle(Object itemId, Object propertyId) {
                if (propertyId == MODIFIED_PROPERTY) {
                    return "email";
                }
                return null;
            }
        });

    }
}
