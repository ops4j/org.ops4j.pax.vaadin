package com.vaadin.demo.sampler.features.popupviews;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.PopupView;

@SuppressWarnings("serial")
public class PopupViewContents extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "PopupView content modes";
    }

    @Override
    public String getDescription() {
        return "The PopupView supports both static and dynamically generated HTML content for the minimized view.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(PopupView.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { PopupViewClosing.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }
}
