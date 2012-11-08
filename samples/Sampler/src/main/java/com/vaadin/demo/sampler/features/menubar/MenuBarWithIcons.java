package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.MenuBar;

@SuppressWarnings("serial")
public class MenuBarWithIcons extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "MenuBar with icons";
    }

    @Override
    public String getDescription() {
        return "You can add icons to individual MenuBar items, to make it faster for the user to distinguish separate items.";
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
}
