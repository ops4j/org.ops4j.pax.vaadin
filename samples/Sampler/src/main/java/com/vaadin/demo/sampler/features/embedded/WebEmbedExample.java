package com.vaadin.demo.sampler.features.embedded;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;

public class WebEmbedExample extends VerticalLayout {

    public WebEmbedExample() {
        Embedded e = new Embedded("Vaadin web site", new ExternalResource(
                "http://vaadin.com"));
        e.setType(Embedded.TYPE_BROWSER);
        e.setWidth("100%");
        e.setHeight("400px");
        addComponent(e);
    }
}
