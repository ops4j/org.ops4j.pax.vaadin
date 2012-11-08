package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.MenuBar;

@SuppressWarnings("serial")
public class MenuBarCollapsing extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "MenuBar, collapsing items";
    }

    @Override
    public String getDescription() {
        return "If the root level menu has more items that can fit in view (and if the MenuBar has a specified width), overflowing items will be collapsed to a generated sub-menu last in the root menu, indicated by an arrow.<br /><br/>Resize the browser window to collapse/expand more items.";
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
