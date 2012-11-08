package com.vaadin.demo.sampler.features.embedded;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ImageEmbedExample extends VerticalLayout {

    public ImageEmbedExample() {
        Embedded e = new Embedded("Image from a theme resource",
                new ThemeResource("../runo/icons/64/document.png"));
        addComponent(e);
    }
}
