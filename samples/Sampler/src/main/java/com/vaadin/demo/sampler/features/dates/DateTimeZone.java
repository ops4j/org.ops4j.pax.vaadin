package com.vaadin.demo.sampler.features.dates;

import java.util.TimeZone;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.PopupDateField;

public class DateTimeZone extends Feature {

    @Override
    public String getName() {
        return "Date selection, time zone";
    }

    @Override
    public String getDescription() {
        return "In this example, you can select a different time zone from the"
                + " combo box and see how the selected time changes.";
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };

    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(PopupDateField.class),
                new APIResource(InlineDateField.class),
                new APIResource(TimeZone.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return getSiblingFeatures();
    }

    @Override
    public Version getSinceVersion() {
        return Version.V67;
    }

}
