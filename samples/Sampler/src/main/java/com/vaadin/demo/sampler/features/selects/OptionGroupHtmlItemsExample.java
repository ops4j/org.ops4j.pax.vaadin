package com.vaadin.demo.sampler.features.selects;

import java.util.Arrays;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

public class OptionGroupHtmlItemsExample extends VerticalLayout {

    private static final List<String> cities = Arrays.asList(new String[] {
            "<u>Berlin</u>", "Brussels", "<b>Helsinki</b>", "Madrid",
            "<s>Oslo</s>", "Paris", "<i>Stockholm</i>" });

    public OptionGroupHtmlItemsExample() {
        setSpacing(true);

        final CheckBox htmlAllowedBox = new CheckBox("HTML content allowed",
                false);
        htmlAllowedBox.setImmediate(true);

        addComponent(htmlAllowedBox);

        final OptionGroup singleCitySelect = new OptionGroup(
                "Please select a city", cities);

        singleCitySelect.select("Berlin");

        addComponent(singleCitySelect);

        final OptionGroup multipleCitySelect = new OptionGroup(
                "Please select cities", cities);

        multipleCitySelect.setMultiSelect(true);
        multipleCitySelect.setNullSelectionAllowed(false);
        multipleCitySelect.select("Berlin");

        addComponent(multipleCitySelect);

        htmlAllowedBox.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Boolean htmlAllowed = (Boolean) htmlAllowedBox.getValue();
                singleCitySelect.setHtmlContentAllowed(htmlAllowed
                        .booleanValue());
                multipleCitySelect.setHtmlContentAllowed(htmlAllowed
                        .booleanValue());
            }
        });
    }

}
