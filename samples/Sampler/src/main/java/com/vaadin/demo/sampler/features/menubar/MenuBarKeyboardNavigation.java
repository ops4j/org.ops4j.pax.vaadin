package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;

public class MenuBarKeyboardNavigation extends Feature {

    @Override
    public String getDescription() {
        return "As well as using the mouse you can also use the "
                + "keyboard to select items from the menu bar. Make sure "
                + "that the menu bar has keyboard focus and use the arrow "
                + "keys to navigate in the menu. To select an item use the "
                + "Enter and to close the menu use the Esc key.";
    }

    @Override
    public String getName() {
        return "MenuBar keyboard navigation";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(MenuBar.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return getSiblingFeatures();
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public Component getExample() {
        return new MenuBarWithIconsExample();
    }

}
