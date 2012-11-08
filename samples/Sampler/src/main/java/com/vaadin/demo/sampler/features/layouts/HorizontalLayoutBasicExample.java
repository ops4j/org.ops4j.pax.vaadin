package com.vaadin.demo.sampler.features.layouts;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class HorizontalLayoutBasicExample extends HorizontalLayout {

    public HorizontalLayoutBasicExample() {
        // this is a HorizontalLayout

        // First TextField
        TextField tf = new TextField();
        tf.setWidth("70px");
        addComponent(tf);

        // A dash
        Label dash = new Label("-");
        addComponent(dash);
        setComponentAlignment(dash, Alignment.MIDDLE_LEFT);

        // Second TextField
        tf = new TextField();
        tf.setWidth("70px");
        addComponent(tf);

        // Another dash
        dash = new Label("-");
        addComponent(dash);
        setComponentAlignment(dash, Alignment.MIDDLE_LEFT);

        // Third TextField
        tf = new TextField();
        tf.setWidth("70px");
        addComponent(tf);

        // Yet another dash
        dash = new Label("-");
        addComponent(dash);
        setComponentAlignment(dash, Alignment.MIDDLE_LEFT);

        // Forth and last TextField
        tf = new TextField();
        tf.setWidth("70px");
        addComponent(tf);

    }
}
