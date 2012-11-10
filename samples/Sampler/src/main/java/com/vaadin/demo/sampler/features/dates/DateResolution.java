package com.vaadin.demo.sampler.features.dates;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.PopupDateField;

@SuppressWarnings("serial")
public class DateResolution extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Date selection, resolution";
    }

    @Override
    public String getDescription() {
        return "In this example, you can select a different resolution"
                + " from the combo box and see how the calendar component"
                + " changes.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(InlineDateField.class),
                new APIResource(PopupDateField.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DateInline.class, DatePopup.class,
                DatePopupInputPrompt.class, DateLocale.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }
}
