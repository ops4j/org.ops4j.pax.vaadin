package com.vaadin.demo.sampler.features.form;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;

@SuppressWarnings("serial")
public class LoginForm extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Login form";
    }

    @Override
    public String getDescription() {
        return "Using normal Vaadin components to build a login form is sometimes sufficient, but in many cases you'll want the browser to remember the credentials later on. Using the LoginForm helps in that case. You can override methods from LoginForm if you wish to specify the generated HTML yourself.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(
                com.vaadin.ui.LoginForm.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return null;
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
