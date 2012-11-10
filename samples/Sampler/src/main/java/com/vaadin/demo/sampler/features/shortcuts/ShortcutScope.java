package com.vaadin.demo.sampler.features.shortcuts;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.AbstractField.FocusShortcut;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickShortcut;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ShortcutScope extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getName() {
        return "Shortcuts, scope";
    }

    @Override
    public String getDescription() {
        return "Here, identical shortcuts work independently"
                + " within each panel; they are <i>scoped</i>"
                + " to the panel."
                + "<p>ALT-SHIFT-1 focuses the first panel, ALT-SHIFT-2"
                + " the second, and within the panels arrow-down"
                + " advances and ALT-SHIFT-F/ALT-SHIFT-L focuses"
                + " firstname/lastname respectively. "
                + "ALT-SHIFT-S saves each panel.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(FocusShortcut.class),
                new APIResource(ClickShortcut.class),
                new APIResource(ShortcutListener.class),
                new APIResource(Panel.class), new APIResource(Button.class),
                new APIResource(TextField.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { ShortcutBasics.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
