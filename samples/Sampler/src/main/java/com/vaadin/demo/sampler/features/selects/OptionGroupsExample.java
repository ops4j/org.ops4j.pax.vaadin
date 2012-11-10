package com.vaadin.demo.sampler.features.selects;

import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class OptionGroupsExample extends VerticalLayout implements
        Property.ValueChangeListener {

    private static final List<String> cities = Arrays.asList(new String[] {
            "Berlin", "Brussels", "Helsinki", "Madrid", "Oslo", "Paris",
            "Stockholm" });

    public OptionGroupsExample() {
        setSpacing(true);

        // 'Shorthand' constructor - also supports data binding using Containers
        OptionGroup citySelect = new OptionGroup("Please select a city", cities);

        citySelect.setNullSelectionAllowed(false); // user can not 'unselect'
        citySelect.select("Berlin"); // select this by default
        citySelect.setImmediate(true); // send the change to the server at once
        citySelect.addListener(this); // react when the user selects something

        addComponent(citySelect);

        addComponent(new Label("<h3>Multi-selection</h3>", Label.CONTENT_XHTML));

        // Create the multiselect option group
        // 'Shorthand' constructor - also supports data binding using Containers
        citySelect = new OptionGroup("Please select cities", cities);

        citySelect.setMultiSelect(true);
        citySelect.setNullSelectionAllowed(false); // user can not 'unselect'
        citySelect.select("Berlin"); // select this by default
        citySelect.setImmediate(true); // send the change to the server at once
        citySelect.addListener(this); // react when the user selects something

        addComponent(citySelect);
    }

    /*
     * Shows a notification when a selection is made. The listener will be
     * called whenever the value of the component changes, i.e when the user
     * makes a new selection.
     */
    public void valueChange(ValueChangeEvent event) {
        getWindow().showNotification("Selected city: " + event.getProperty());

    }
}
