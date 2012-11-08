package com.vaadin.demo.sampler.features.commons;

import com.vaadin.data.Validatable;
import com.vaadin.data.Validator;
import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.FeatureSet;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.demo.sampler.features.form.FormPojoExample;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;

@SuppressWarnings("serial")
public class Validation extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Validation";
    }

    private static final String desc = "<p>Field components can have <i>validators</i> that check"
            + " the values entered by a user. Validation is most useful when used within a Form, but"
            + " you can use validation for single stand-alone fields as well.</p>";

    @Override
    public Component getExample() {
        return new FormPojoExample();
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Validatable.class),
                new APIResource(Validator.class), new APIResource(Form.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { Errors.class, FeatureSet.Forms.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
