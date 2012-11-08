package com.vaadin.demo.sampler.features.layouts;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.FeatureSet.Layouts;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.AbsoluteLayout;

@SuppressWarnings("serial")
public class AbsoluteLayoutBasic extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getName() {
        return "Absolute layout";
    }

    @Override
    public String getDescription() {
        return "The AbsoluteLayout allows you to position other components relatively inside the layout using coordinates. Note, that you must specify an explicit size for the layout, undefined size will not work.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(AbsoluteLayout.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { Layouts.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new NamedExternalResource(
                "CSS for the layout", getThemeBase()
                        + "layouts/absoluteexample.css") };
    }
}
