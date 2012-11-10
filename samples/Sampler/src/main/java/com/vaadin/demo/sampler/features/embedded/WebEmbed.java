package com.vaadin.demo.sampler.features.embedded;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.demo.sampler.features.commons.JSApi;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

@SuppressWarnings("serial")
public class WebEmbed extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "Web content";
    }

    @Override
    public String getDescription() {
        return "Web pages can be embedded, allowing easy integration to older systems. Synchronization and messaging between web pages and Vaadin apps can be accomplished with Vaadin JS APIs. Just use Embedded.TYPE_BROWSER";
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
        return new Class[] { ImageEmbed.class, FlashEmbed.class, JSApi.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
