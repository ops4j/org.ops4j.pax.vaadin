package com.vaadin.demo.sampler.features.text;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.TextField;

public class TextFieldTextChangeEvent extends Feature {

    @Override
    public String getName() {
        return "Instant text field";
    }

    @Override
    public String getDescription() {
        return "You can react to the text input in a text field as the user is writing. This allows for easy implementation "
                + "of for instance as-you-type filtering.";
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(TextField.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return null;
    }

    @Override
    public Version getSinceVersion() {
        return Version.V65;
    }

}
