package com.vaadin.demo.sampler.features.tabsheets;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.TabSheet;

@SuppressWarnings("serial")
public class TabSheetClosing extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "Tabsheet, closable tabs";
    }

    @Override
    public String getDescription() {
        return "Individual tabs can be set closable. You can also add a handler to perform additional tasks when a user closes a tab, or even prevent closing if for instance the tab contains unsaved data.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(TabSheet.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { TabSheetScrolling.class, TabSheetIcons.class,
                TabSheetDisabled.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
