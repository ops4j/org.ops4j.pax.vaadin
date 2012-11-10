package com.vaadin.demo.sampler.features.dates;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.PopupDateField;

@SuppressWarnings("serial")
public class DatePopupKeyboardNavigation extends Feature {

    @Override
    public String getDescription() {
        return "You can use the keyboard to navigate the DateField.<br/>"
                + "Focus the textfield and press the down arrow key to bring "
                + "up the popup. Then, use the arrow keys to move between days "
                + "and weeks. <br/>To directly jump between months use shift+left/right "
                + "arrow keys and to jump between years use shift+up/down arrow keys."
                + "<br/>To select the date press Enter, to cancel the selection press Escape "
                + "or to restore the selection press Backspace.";
    }

    @Override
    public String getName() {
        return "Pop-up date keyboard navigation";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(InlineDateField.class),
                new APIResource(PopupDateField.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DatePopupInputPrompt.class, DateInline.class,
                DateLocale.class, DateResolution.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public Component getExample() {
        return new FocusedDatePopupExample();
    }

    private class FocusedDatePopupExample extends DatePopupExample {
        @Override
        public void attach() {
            super.attach();
            PopupDateField f = (PopupDateField) getComponentIterator().next();
            f.focus();
        }
    }

}
