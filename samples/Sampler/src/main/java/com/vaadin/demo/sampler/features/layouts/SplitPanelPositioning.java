package com.vaadin.demo.sampler.features.layouts;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalSplitPanel;

public class SplitPanelPositioning extends Feature {

    @Override
    public String getName() {
        return "Split panel, positioning";
    }

    @Override
    public String getDescription() {
        return "The SplitPanels splitter can be positioned either from the left or from the right."
                + "The position can either be given in pixels or percentage.";
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new NamedExternalResource(
                "CSS for the layout", getThemeBase()
                        + "layouts/splitpanelpositioningexample.css") };
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(HorizontalSplitPanel.class),
                new APIResource(VerticalSplitPanel.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { SplitPanelBasic.class };
    }

    @Override
    public Version getSinceVersion() {
        return Version.V65;
    }

}
