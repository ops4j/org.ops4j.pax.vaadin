package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.MenuBar;

@SuppressWarnings("serial")
public class MenuBarHiddenItems extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "MenuBar, hidden items";
    }

    @Override
    public String getDescription() {
        return "Individual menu items can be enabled, disabled, visible or hidden.";
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
