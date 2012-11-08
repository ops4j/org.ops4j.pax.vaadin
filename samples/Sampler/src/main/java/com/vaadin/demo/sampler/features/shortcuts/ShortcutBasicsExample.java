package com.vaadin.demo.sampler.features.shortcuts;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.ui.AbstractField.FocusShortcut;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ShortcutBasicsExample extends VerticalLayout {

    public ShortcutBasicsExample() {
        setSpacing(true);

        // Firstname input with an input prompt for demo clarity
        final TextField firstname = new TextField("Firstname");
        firstname.setInputPrompt("ALT-SHIFT-F to focus");
        addComponent(firstname);
        // Add global shortcut that focuses the field
        firstname.addShortcutListener(new FocusShortcut(firstname, KeyCode.F,
                ModifierKey.ALT, ModifierKey.SHIFT));

        // Lastname input with an input prompt for demo clarity
        final TextField lastname = new TextField("Lastname");
        lastname.setInputPrompt("ALT-SHIFT-L to focus");
        addComponent(lastname);
        // Add global shortcut that focuses the field
        lastname.addShortcutListener(new FocusShortcut(lastname, KeyCode.L,
                ModifierKey.ALT, ModifierKey.SHIFT));

        // Button with a simple click-listener
        final Button enter = new Button("Enter", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                getWindow().showNotification("Enter button clicked");
            }
        });
        addComponent(enter);
        enter.setStyleName("primary"); // make it look like it's default
        // Add global shortcut using the built-in helper
        enter.setClickShortcut(KeyCode.ENTER);
        // which is easier to find, but otherwise equal to:
        // enter.addShortcutListener(new ClickShortcut(search,KeyCode.ENTER));

    }

}
