package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.MenuBar;

public class MenuBarCheckableItems extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V66;
    }

    @Override
    public String getName() {
        return "MenuBar checkable items";
    }

    @Override
    public String getDescription() {
        return "Individual MenuBar menu items can be marked as checkable.";
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
        return new NamedExternalResource[] {};
    }
}
