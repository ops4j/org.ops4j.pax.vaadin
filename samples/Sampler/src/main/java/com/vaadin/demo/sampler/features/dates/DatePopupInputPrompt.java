package com.vaadin.demo.sampler.features.dates;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt;
import com.vaadin.demo.sampler.features.text.TextFieldInputPrompt;
import com.vaadin.ui.PopupDateField;

@SuppressWarnings("serial")
public class DatePopupInputPrompt extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public String getName() {
        return "Pop-up date selection with input prompt";
    }

    @Override
    public String getDescription() {
        return " The PopupDateField can have an <i>input prompt</i> - a textual hint that is shown within"
                + " the field when the field is otherwise empty.<br/>"
                + " You can use an input prompt instead of a caption to save"
                + " space, but only do so if the function of the PopupDateField is"
                + " still clear when a value has been entered and the prompt is no"
                + " longer visible.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(PopupDateField.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DateInline.class, DateLocale.class,
                DateResolution.class, TextFieldInputPrompt.class,
                ComboBoxInputPrompt.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new NamedExternalResource(
                "UI Patterns, Input Prompt",
                "http://ui-patterns.com/pattern/InputPrompt") };
    }

}
