package com.vaadin.demo.sampler.features.treetable;

import java.util.Arrays;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbstractSelect.MultiSelectMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;

public class TreeTableSelectionExample extends TreeTableBasicExample {

    private OptionGroup selectMode;

    private OptionGroup multiSelectMode;

    /**
     * This example example extends the TreeTableBasic example. The source code
     * of TreeTableBasic can be found as its own sample in the sampler
     */
    public TreeTableSelectionExample() {
        super();

        setSpacing(true);

        treetable.setSelectable(true);
        treetable.setMultiSelect(false);

        HorizontalLayout controls = new HorizontalLayout();
        controls.setSpacing(true);
        addComponentAsFirst(controls);

        // Select mode
        selectMode = new OptionGroup("Select mode", Arrays.asList(
                "Single select", "Multiselect"));
        selectMode.setValue("Single select");
        selectMode.setImmediate(true);
        selectMode.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty().getValue().equals("Single select")) {
                    treetable.setMultiSelect(false);
                    multiSelectMode.setEnabled(false);
                } else {
                    treetable.setMultiSelect(true);
                    multiSelectMode.setEnabled(true);
                }
            }
        });
        controls.addComponent(selectMode);

        multiSelectMode = new OptionGroup("Multi-select Mode", Arrays.asList(
                "Default mode", "Simple mode"));
        multiSelectMode.setValue("Default mode");
        multiSelectMode.setEnabled(false);
        multiSelectMode.setImmediate(true);
        multiSelectMode.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty().getValue().equals("Default mode")) {
                    treetable.setMultiSelectMode(MultiSelectMode.DEFAULT);
                } else {
                    treetable.setMultiSelectMode(MultiSelectMode.SIMPLE);
                }
            }
        });
        controls.addComponent(multiSelectMode);
    }
}
