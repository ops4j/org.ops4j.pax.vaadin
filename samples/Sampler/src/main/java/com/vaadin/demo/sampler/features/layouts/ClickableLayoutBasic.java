package com.vaadin.demo.sampler.features.layouts;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.FeatureSet.Layouts;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ClickableLayoutBasic extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getDescription() {
        return "You can listen for click events by attaching a LayoutClickListener to your layout.";
    }

    @Override
    public String getName() {
        return "Clickable layouts";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(CssLayout.class),
                new APIResource(AbsoluteLayout.class),
                new APIResource(VerticalLayout.class),
                new APIResource(HorizontalLayout.class),
                new APIResource(GridLayout.class), new APIResource(Panel.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { Layouts.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new NamedExternalResource(
                "CSS for the layout", getThemeBase()
                        + "layouts/clickableexample.css") };
    }

}
