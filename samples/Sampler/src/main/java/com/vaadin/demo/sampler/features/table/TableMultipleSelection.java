package com.vaadin.demo.sampler.features.table;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.FeatureSet;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class TableMultipleSelection extends Feature {

    @Override
    public String getDescription() {
        return "When the table is in multiselect mode you can use the Ctrl (or Meta key) to select or unselect multiple items."
                + "You can also use the Shift key to select a range of items and Ctrl+Shift key to select multiple ranges.";
    }

    @Override
    public String getName() {
        return "Table, multiple selection";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Table.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { FeatureSet.Tables.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };
    }

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public Component getExample() {
        return new TableMainFeaturesExample();
    }

}
