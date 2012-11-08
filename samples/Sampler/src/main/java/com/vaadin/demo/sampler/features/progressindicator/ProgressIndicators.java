package com.vaadin.demo.sampler.features.progressindicator;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.ProgressIndicator;

@SuppressWarnings("serial")
public class ProgressIndicators extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "Progress indication";
    }

    @Override
    public String getDescription() {
        return "The ProgressIndicator component can be used to inform the user of actions that take a long time to finish, such as file uploads or search queries.<br /><br />Updates to the indicator happen via polling, and the default polling interval is 1 second.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(ProgressIndicator.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return null;
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
