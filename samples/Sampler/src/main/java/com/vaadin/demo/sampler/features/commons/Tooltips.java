package com.vaadin.demo.sampler.features.commons;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.AbstractComponent;

@SuppressWarnings("serial")
public class Tooltips extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Tooltips";
    }

    @Override
    public String getDescription() {
        return "Most components can have a <i>description</i>,"
                + " which is usually shown as a <i>\"tooltip\"</i>."
                + " In the Form component, the description is shown at the"
                + " top of the form."
                + " Descriptions can have HTML formatted ('rich') content.<br/>"
                + "";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(AbstractComponent.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
