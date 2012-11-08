package com.vaadin.demo.sampler.features.shortcuts;

import java.util.Iterator;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.AbstractField.FocusShortcut;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickShortcut;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ShortcutScopeExample extends VerticalLayout {

    public ShortcutScopeExample() {
        setSpacing(true);

        // We want the panels side-by-side
        HorizontalLayout hz = new HorizontalLayout();
        hz.setSpacing(true);
        addComponent(hz);

        // Add two identical panels
        hz.addComponent(createPanel(1));
        hz.addComponent(createPanel(2));

    }

    private Panel createPanel(final int number) {
        final Panel p = new Panel("Panel " + number);
        ((VerticalLayout) p.getContent()).setSpacing(true);

        // Let's create a customized shortcut that jumps to the next field
        p.addAction(new ShortcutListener("Next field", KeyCode.ARROW_DOWN, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                // The panel is the sender, loop trough content
                for (Iterator<Component> it = ((ComponentContainer) sender)
                        .getComponentIterator(); it.hasNext();) {
                    // target is the field we're currently in, focus the next
                    if (it.next() == target && it.hasNext()) {
                        Object next = it.next();
                        if (next instanceof Focusable) {
                            ((Focusable) next).focus();
                        }
                    }
                }
            }
        });

        // Firstname input with an input prompt for demo clarity
        final TextField firstname = new TextField("Firstname");
        firstname.setInputPrompt("ALT-SHIFT-F to focus");
        p.addComponent(firstname);
        // Using firstname.addShortcutListener() would add globally,
        // but we want the shortcut only in this panel:
        p.addAction(new FocusShortcut(firstname, KeyCode.F, ModifierKey.ALT,
                ModifierKey.SHIFT));
        // additinally we'll add a global shortcut for this field using the
        // shorthand notation (^1 == CTRL-1, etc)
        firstname.addShortcutListener(new FocusShortcut(firstname,
                "Focus panel &_" + number));
        p.setDescription("CTRL-" + number + " to focus");

        // Lastname input with an input prompt for demo clarity
        final TextField lastname = new TextField("Lastname");
        lastname.setInputPrompt("ALT-SHIFT-L to focus");
        p.addComponent(lastname);
        // Using firstname.addShortcutListener() would add globally,
        // but we want the shortcut only in this panel:
        p.addAction(new FocusShortcut(lastname, KeyCode.L, ModifierKey.ALT,
                ModifierKey.SHIFT));

        // Button with a simple click-listener
        final Button save = new Button("Save", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                getWindow().showNotification(p.getCaption() + " save clicked");
            }
        });
        p.addComponent(save);

        // setClickShortcut() would add global shortcut, instead we
        // 'scope' the shortcut to the panel:
        p.addAction(new ClickShortcut(save, KeyCode.S, ModifierKey.ALT,
                ModifierKey.SHIFT));

        return p;
    }

}
