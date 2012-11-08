package com.vaadin.demo.sampler.features.commons;

import com.vaadin.terminal.UserError;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ErrorsExample extends VerticalLayout {

    public ErrorsExample() {
        setSpacing(true);

        addComponent(new Label("<h3>Errors in caption</h3>",
                Label.CONTENT_XHTML));
        addComponent(new Label(
                "Error indicators are usually placed on the right side of the component's caption."));

        TextField input = new TextField("Field caption");
        input.setComponentError(new UserError("This field is never satisfied"));
        addComponent(input);

        addComponent(new Label("<h3>Errors without caption</h3>",
                Label.CONTENT_XHTML));
        addComponent(new Label(
                "If the component has no caption, the error indicator is usually placed on the right side of the component."));

        input = new TextField();
        input.setInputPrompt("This field has an error");
        input.setComponentError(new UserError("This field is never satisfied."));
        addComponent(input);

        addComponent(new Label(
                "<h3>Error icon placement depends on the layout</h3>",
                Label.CONTENT_XHTML));
        addComponent(new Label(
                "FormLayout for example places the error between the component caption and the actual field."));

        FormLayout fl = new FormLayout();
        fl.setMargin(false);
        fl.setSpacing(false);
        addComponent(fl);
        input = new TextField("Field caption");
        input.setInputPrompt("This field has an error");
        input.setComponentError(new UserError("This field is never satisfied."));
        fl.addComponent(input);

    }
}
