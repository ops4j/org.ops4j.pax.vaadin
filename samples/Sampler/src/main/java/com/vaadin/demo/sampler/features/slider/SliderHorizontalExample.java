package com.vaadin.demo.sampler.features.slider;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;

@SuppressWarnings("serial")
public class SliderHorizontalExample extends HorizontalLayout {

    public SliderHorizontalExample() {
        setSpacing(true);
        setWidth("100%");

        final Label value = new Label("0");
        value.setWidth("3em");

        final Slider slider = new Slider("Select a value between 0 and 100");
        slider.setWidth("100%");
        slider.setMin(0);
        slider.setMax(100);
        slider.setImmediate(true);
        slider.addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                value.setValue(event.getProperty().getValue());
            }
        });

        addComponent(slider);
        setExpandRatio(slider, 1);
        addComponent(value);
        setComponentAlignment(value, Alignment.BOTTOM_LEFT);

    }

}
