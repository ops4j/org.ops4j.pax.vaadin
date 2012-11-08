package com.vaadin.demo.sampler.features.layouts;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LayoutSpacingExample extends VerticalLayout {

    public LayoutSpacingExample() {

        // Create a grid layout.
        final GridLayout grid = new GridLayout(3, 3);

        // Enable spacing for the example layout (this is the one we'll toggle
        // with the checkbox)
        grid.setSpacing(true);

        // CheckBox for toggling spacing on and off
        final CheckBox spacing = new CheckBox("Spacing enabled");
        spacing.setValue(true);
        spacing.setImmediate(true);
        spacing.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                grid.setSpacing(spacing.booleanValue());
            }
        });
        addComponent(spacing);

        // Add the layout to the containing layout.
        addComponent(grid);

        // Populate the layout with components.
        for (int i = 0; i < 9; i++) {
            grid.addComponent(new Button("Component " + (i + 1)));
        }

        setSpacing(true); // enable spacing for the example itself
    }
}
