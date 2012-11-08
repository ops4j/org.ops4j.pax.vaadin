package com.vaadin.demo.sampler.features.buttons;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CheckBoxesExample extends VerticalLayout implements
        Button.ClickListener {

    private static final String CAPTION = "Allow HTML";
    private static final String TOOLTIP = "Allow/disallow HTML in comments";
    private static final ThemeResource ICON = new ThemeResource(
            "../sampler/icons/page_code.gif");

    public CheckBoxesExample() {
        setSpacing(true);

        // Button w/ text and tooltip
        CheckBox cb = new CheckBox(CAPTION);
        cb.setDescription(TOOLTIP);
        cb.setImmediate(true);
        cb.addListener(this); // react to clicks
        addComponent(cb);

        // Button w/ text, icon and tooltip
        cb = new CheckBox(CAPTION);
        cb.setDescription(TOOLTIP);
        cb.setIcon(ICON);
        cb.setImmediate(true);
        cb.addListener(this); // react to clicks
        addComponent(cb);

        // Button w/ icon and tooltip
        cb = new CheckBox();
        cb.setDescription(TOOLTIP);
        cb.setIcon(ICON);
        cb.setImmediate(true);
        cb.addListener(this); // react to clicks
        addComponent(cb);

    }

    /*
     * Shows a notification when a checkbox is clicked.
     */
    public void buttonClick(ClickEvent event) {
        boolean enabled = event.getButton().booleanValue();
        getWindow().showNotification(
                "HTML " + (enabled ? "enabled" : "disabled"));
    }
}
