package com.vaadin.demo.sampler.features.slider;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Slider;

@SuppressWarnings("serial")
public class SliderHorizontal extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "Slider";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Slider.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { SliderVertical.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
