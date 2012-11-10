package com.vaadin.demo.sampler.features.form;

import com.vaadin.data.Validatable;
import com.vaadin.data.Validator;
import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.FeatureSet;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.demo.sampler.features.commons.Errors;
import com.vaadin.demo.sampler.features.commons.Validation;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;

@SuppressWarnings("serial")
public class FormAdvancedLayout extends Feature {

    @Override
    public String getName() {
        return "Form with advanced layout";
    }

    @Override
    public Component getExample() {
        return new FormAdvancedLayoutExample();
    }

    @Override
    public String getDescription() {
        return "When the form becomes more complex you need more control over how the fields are laid out."
                + " The basic form automatically lays out the fields in the given layout but you can override the layout function in form to provide your own layout rules.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Validatable.class),
                new APIResource(Validator.class), new APIResource(Form.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { FormBasic.class, Validation.class, Errors.class,
                FeatureSet.Forms.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };
    }

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }
}
