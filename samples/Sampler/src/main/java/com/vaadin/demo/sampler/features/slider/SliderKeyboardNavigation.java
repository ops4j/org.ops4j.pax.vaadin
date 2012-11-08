package com.vaadin.demo.sampler.features.slider;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Slider;

public class SliderKeyboardNavigation extends Feature {

    @Override
    public String getDescription() {
        return "You can use the keyboard to adjust the slider by ensuring that the slider"
                + " has keyboard focus and then using the arrow keys to move. To accelerate the"
                + " movement hold the shift key while pressing the arrow keys.";
    }

    @Override
    public String getName() {
        return "Slider, keyboard navigation";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Slider.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { SliderHorizontal.class, SliderVertical.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public Component getExample() {
        return new FocusedSliderHorizontalExample();
    }

    private class FocusedSliderHorizontalExample extends
            SliderHorizontalExample {
        @Override
        public void attach() {
            super.attach();
            Slider s = (Slider) getComponentIterator().next();
            s.focus();
        }
    }

}
