package com.vaadin.demo.sampler.features.selects;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.OptionGroup;

@SuppressWarnings("serial")
public class OptionGroups extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "Option group";
    }

    @Override
    public String getDescription() {
        return "OptionGroup component present a group of selections with either radio buttons or checkboxes.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(OptionGroup.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { OptionGroupDisabledItems.class,
                OptionGroupHtmlItems.class, NativeSelection.class,
                ListSelectMultiple.class, TwinColumnSelect.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
