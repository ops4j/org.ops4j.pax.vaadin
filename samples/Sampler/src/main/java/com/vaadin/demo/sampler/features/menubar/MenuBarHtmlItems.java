package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.MenuBar;

public class MenuBarHtmlItems extends Feature {

    @Override
    public String getName() {
        return "MenuBar, HTML items";
    }

    @Override
    public String getDescription() {
        return "<p>A MenuBar does by default display the item captions as"
                + " plain text without any formatting. By using"
                + " <i>allowHtmlContent</i>, the items can instead be set to"
                + " use HTML.</p>"
                + "<p>When enabling HTML content in the item captions, the"
                + " developer should take care to avoid cross-site scripting"
                + " vulnerabilities if the contents of the captions can be"
                + " entered by a possibly malicious user.</p>";
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new NamedExternalResource(
                "Cross-site scripting",
                "http://en.wikipedia.org/wiki/Cross-site_scripting") };
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(MenuBar.class) };
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
