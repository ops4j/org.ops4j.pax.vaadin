package com.vaadin.demo.sampler.features.commons;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.demo.sampler.features.form.FormBasic;
import com.vaadin.demo.sampler.features.notifications.NotificationError;
import com.vaadin.ui.AbstractComponent;

@SuppressWarnings("serial")
public class Errors extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    private static final String desc = "<p>You can set a <i>component error</i> property to"
            + " indicate an error situation - an error indicator icon will appear"
            + " beside or inside the component, depending on the component and the containing layout,"
            + " and an error message will appear as a 'tooltip' when the mouse pointer"
            + " hovers over the component.</p>"
            + "<p>You can set the error on almost any component, but please note"
            + " that, from a usability standpoint, it may not always be the best"
            + " solution."
            + " It is usually not a good idea to set an error"
            + " on a Button: the user can not click 'Save' differently to"
            + " correct the error."
            + " The <i>component error</i> property is most useful for indicating what component is"
            + " causing an error, so that the user"
            + " can find and correct the problem.</p>"
            + "<p>If there is no specific component that causes the error, consider using a"
            + " (styled) Label or a Notification to indicate the error.</p>"
            + "<p>The Form component displays an error of a contained field in"
            + " a special error indicator area at the bottom of the form."
            + " If there are multiple fields with an error, only the first is displayed.</p>";

    @Override
    public String getName() {
        return "Error indicator";
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(AbstractComponent.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { Validation.class, FormBasic.class,
                NotificationError.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
