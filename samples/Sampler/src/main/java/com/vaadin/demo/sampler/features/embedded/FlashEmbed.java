package com.vaadin.demo.sampler.features.embedded;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

@SuppressWarnings("serial")
public class FlashEmbed extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "Flash";
    }

    @Override
    public String getDescription() {
        return "Flash movies, such as YouTube videos, can easily be embedded to your applications.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Embedded.class),
                new APIResource(ThemeResource.class),
                new APIResource(ClassResource.class),
                new APIResource(ExternalResource.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { ImageEmbed.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
