package com.vaadin.demo.sampler.features.commons;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.terminal.gwt.server.WebBrowser;

public class BrowserInformation extends Feature {

    @Override
    public String getDescription() {
        return "Browser differences are mostly hidden by Vaadin but in some cases it is valuable to get information on the browser the user is using."
                + " In themes special CSS rules are used but it is also possible to get information about the browser in the application code."
                + " This sample displays the browser name, ip address and the screen size you are using, and your TimeZone offset. The information is available on server side.";
    }

    @Override
    public String getName() {
        return "Browser information";
    }

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(WebBrowser.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return null;
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
