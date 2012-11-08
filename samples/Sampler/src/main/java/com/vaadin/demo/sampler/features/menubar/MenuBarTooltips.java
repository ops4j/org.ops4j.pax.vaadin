package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.MenuBar;

public class MenuBarTooltips extends Feature {

    @Override
    public String getName() {
        return "MenuBar, tooltips";
    }

    @Override
    public String getDescription() {
        return "You can add tooltips to all items in the menu bar. "
                + "Hoover the mouse over an item to see the tooltip.";
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
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
    public Version getSinceVersion() {
        return Version.V65;
    }

}
