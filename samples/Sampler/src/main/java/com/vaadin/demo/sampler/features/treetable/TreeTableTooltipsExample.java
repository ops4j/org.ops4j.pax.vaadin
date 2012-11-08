package com.vaadin.demo.sampler.features.treetable;

import com.vaadin.data.Item;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Component;

public class TreeTableTooltipsExample extends TreeTableBasicExample {

    /**
     * This example example extends the TreeTableBasic example. The source code
     * of TreeTableBasic can be found as its own sample in the sampler
     */
    public TreeTableTooltipsExample() {

        // treetable created in TreeTableBasic
        treetable.setItemDescriptionGenerator(new ItemDescriptionGenerator() {

            public String generateDescription(Component source, Object itemId,
                    Object propertyId) {
                Item item = treetable.getItem(itemId);
                if (propertyId != null) {
                    Object value = item.getItemProperty(propertyId).getValue();
                    return "The " + propertyId + " for this item is "
                            + String.valueOf(value);
                }
                return null;
            }
        });
        
    }

}
