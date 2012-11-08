package com.vaadin.demo.sampler.features.selects;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.OptionGroup;

public class OptionGroupHtmlItems extends Feature {

    @Override
    public String getName() {
        return "Option group, HTML items";
    }

    @Override
    public String getDescription() {
        return "<p>An OptionGroup does by default display the item captions as"
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
        return new APIResource[] { new APIResource(OptionGroup.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { OptionGroups.class, OptionGroupDisabledItems.class };
    }

    @Override
    public Version getSinceVersion() {
        return Version.V67;
    }

}
