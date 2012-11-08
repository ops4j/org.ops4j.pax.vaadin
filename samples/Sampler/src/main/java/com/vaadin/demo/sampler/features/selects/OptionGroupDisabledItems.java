package com.vaadin.demo.sampler.features.selects;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.OptionGroup;

public class OptionGroupDisabledItems extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public String getName() {
        return "Option group, disabled items";
    }

    @Override
    public String getDescription() {
        return "OptionGroup component present a group of selections with either radio buttons or checkboxes. It's possible to disable some of the selection items so that the user cannot click these items. In this example, both OptionGroups has two disabled items.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(OptionGroup.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { OptionGroups.class, OptionGroupHtmlItems.class,
                NativeSelection.class, ListSelectMultiple.class,
                TwinColumnSelect.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }
}
