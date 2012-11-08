package com.vaadin.demo.sampler.features.popupviews;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.PopupView;

@SuppressWarnings("serial")
public class PopupViewClosing extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "PopupView close events";
    }

    @Override
    public String getDescription() {
        return "By default the popup will close as soon as the user moves the mouse out of the popup area, but you can set it to close only when the user clicks the mouse outside the popup area. You can also attach open and close listeners for these events.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(PopupView.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { PopupViewContents.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }
}
