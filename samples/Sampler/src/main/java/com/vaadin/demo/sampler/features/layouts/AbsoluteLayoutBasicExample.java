package com.vaadin.demo.sampler.features.layouts;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;

@SuppressWarnings("serial")
public class AbsoluteLayoutBasicExample extends AbsoluteLayout {

    public AbsoluteLayoutBasicExample() {
        setMargin(true);

        // Add a border to the layout with CSS to indicate its boundaries
        addStyleName("border");
        // allow border to show (100% would clip the right side border)
        setWidth("99%");
        setHeight("300px");

        addComponent(new Button("Top: 10px, left: 10px"), "top:10px; left:10px");
        addComponent(new Button("Top: 10px, right: 40px"),
                "top:10px; right:40px");
        addComponent(new Button("Bottom: 0, left: 50%"), "bottom:0; left:50%");
        addComponent(new Button("Top: 50%, right: 50%"), "top:50%; right:50%");

        // Components can overflow out of the container, but they will be
        // clipped. Negative values do not work currently (see issue #4479)
        addComponent(new Button("Top: 50%, right: 50%"), "top:50%; right:50%");
    }

}