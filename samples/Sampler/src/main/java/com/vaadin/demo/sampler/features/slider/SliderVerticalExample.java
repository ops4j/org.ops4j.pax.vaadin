package com.vaadin.demo.sampler.features.slider;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SliderVerticalExample extends VerticalLayout {

    public SliderVerticalExample() {
        setSpacing(true);

        final Label value = new Label("0");
        value.setSizeUndefined();

        final Slider slider = new Slider("Select a value between 0 and 100");
        slider.setOrientation(Slider.ORIENTATION_VERTICAL);
        slider.setHeight("200px");
        slider.setMin(0);
        slider.setMax(100);
        slider.setImmediate(true);
        slider.addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                value.setValue(event.getProperty().getValue());
            }
        });

        addComponent(slider);
        addComponent(value);
        setComponentAlignment(slider, Alignment.TOP_CENTER);
        setComponentAlignment(value, Alignment.TOP_CENTER);

    }

}
