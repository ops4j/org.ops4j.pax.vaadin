package com.vaadin.demo.sampler.features.layouts;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LayoutMarginExample extends GridLayout implements
        Button.ClickListener {

    VerticalLayout marginLayout;
    Button topMargin;
    Button rightMargin;
    Button bottomMargin;
    Button leftMargin;

    public LayoutMarginExample() {
        super(3, 3);
        setWidth("100%");
        setSpacing(true);

        addComponent(
                new Label(
                        "Toggle layout margins with the checkboxes. The right side margin has a theme-specified value, while the other margins are the defaults."),
                0, 0, 2, 0);

        space();
        topMargin = new CheckBox("Top", this);
        topMargin.setValue(true);
        topMargin.setImmediate(true);
        addComponent(topMargin);
        setComponentAlignment(topMargin, Alignment.TOP_CENTER);

        space();
        leftMargin = new CheckBox("Left", this);
        leftMargin.setValue(true);
        leftMargin.setImmediate(true);
        addComponent(leftMargin);
        setComponentAlignment(leftMargin, Alignment.MIDDLE_LEFT);

        marginLayout = new VerticalLayout();
        marginLayout.setStyleName("marginexample");
        marginLayout.setSizeUndefined();
        marginLayout.setMargin(true);
        addComponent(marginLayout);
        marginLayout.addComponent(new Label("Margins all around?"));

        rightMargin = new CheckBox("Right (100px)", this);
        rightMargin.setValue(true);
        rightMargin.setImmediate(true);
        addComponent(rightMargin);
        setComponentAlignment(rightMargin, Alignment.MIDDLE_LEFT);

        space();
        bottomMargin = new CheckBox("Bottom", this);
        bottomMargin.setValue(true);
        bottomMargin.setImmediate(true);
        addComponent(bottomMargin);
        setComponentAlignment(bottomMargin, Alignment.TOP_CENTER);

    }

    public void buttonClick(ClickEvent event) {
        marginLayout.setMargin(topMargin.booleanValue(),
                rightMargin.booleanValue(), bottomMargin.booleanValue(),
                leftMargin.booleanValue());

    }
}
