package com.vaadin.demo.sampler.features.buttons;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;

public class ButtonDisableOnClick extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V67;
    }

    @Override
    public String getName() {
        return "Disable button on click";
    }

    @Override
    public String getDescription() {
        return "A button can be configured to be disabled when clicked. This is useful to prevent (accidental) multiple clicks of the button for a bit longer operations.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Button.class),
                new APIResource(NativeButton.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { ButtonPush.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
