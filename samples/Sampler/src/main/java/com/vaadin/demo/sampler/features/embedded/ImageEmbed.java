package com.vaadin.demo.sampler.features.embedded;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

@SuppressWarnings("serial")
public class ImageEmbed extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "Image";
    }

    @Override
    public String getDescription() {
        return "Add images to your applications using the Embedded component. You can use all the different Resource types Vaadin offers. ThemeResource is usually the easiest choice.";
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
        return new Class[] { FlashEmbed.class, WebEmbed.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
