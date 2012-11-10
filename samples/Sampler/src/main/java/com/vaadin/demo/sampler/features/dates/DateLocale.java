package com.vaadin.demo.sampler.features.dates;

import java.util.Locale;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.PopupDateField;

@SuppressWarnings("serial")
public class DateLocale extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Date selection, locale";
    }

    @Override
    public String getDescription() {
        return "In this example, you can select a different locale"
                + " from the combo box and see how the calendar component"
                + " is localized.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(InlineDateField.class),
                new APIResource(PopupDateField.class),
                new APIResource(Locale.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DateInline.class, DatePopup.class,
                DatePopupInputPrompt.class, DateResolution.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };
    }
}
