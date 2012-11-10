package com.vaadin.demo.sampler.features.layouts;

import java.util.Arrays;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ClickableLayoutBasicExample extends VerticalLayout {

    public ClickableLayoutBasicExample() {
        setMargin(true);
        setSpacing(true);

        // Add a clickable vertical layout
        addComponent(createVerticalClickableLayout());

        // Add a clickable grid layout
        addComponent(createChildComponentClickableLayout());

        // Add a clickable vertical layout
        addComponent(createKeyRegisterClickableLayout());
    }

    private Layout createVerticalClickableLayout() {
        // Create a vertical layout with click events
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("90%");
        layout.setSpacing(true);
        layout.addStyleName("border");
        layout.setMargin(true);

        // Add some components inside the layout
        layout.addComponent(new Label(
                "<b>This is a vertical layout with a click listener attached. "
                        + "Try clicking anywhere inside this layout.</b>",
                Label.CONTENT_RAW));

        final Label clickX = new Label("X-coordinate: <i>Not available.</i>",
                Label.CONTENT_RAW);
        layout.addComponent(clickX);

        final Label clickY = new Label("Y-coordinate: <i>Not available.</i>",
                Label.CONTENT_RAW);
        layout.addComponent(clickY);

        final Label clickRelativeX = new Label(
                "X-coordinate relative to the layout: <i>Not available.</i>",
                Label.CONTENT_RAW);
        layout.addComponent(clickRelativeX);

        final Label clickRelativeY = new Label(
                "Y-coordinate relative to the layout: <i>Not available.</i>",
                Label.CONTENT_RAW);
        layout.addComponent(clickRelativeY);

        final Label button = new Label("Mouse button: <i>Not available.</i>",
                Label.CONTENT_RAW);
        layout.addComponent(button);

        // Listen for layout click events
        layout.addListener(new LayoutClickListener() {
            public void layoutClick(LayoutClickEvent event) {
                // Update components values
                clickX.setValue("X-coordinate: " + event.getClientX());
                clickY.setValue("Y-coordinate: " + event.getClientY());
                clickRelativeX.setValue("X-coordinate relative to the layout: "
                        + event.getRelativeX());
                clickRelativeY.setValue("Y-coordinate relative to the layout: "
                        + event.getRelativeY());
                button.setValue("Mouse button: " + event.getButtonName());

                // Show a notification
                getWindow().showNotification("Layout clicked!");
            }
        });

        return layout;
    }

    private Layout createChildComponentClickableLayout() {

        // Create a grid layout with click events
        GridLayout layout = new GridLayout(5, 2);
        layout.addStyleName("border");
        layout.setSpacing(true);
        layout.setWidth("90%");
        layout.setMargin(true);

        // Add some components to the layout
        layout.addComponent(new Label(
                "<b>Clickable layout events include a reference to the "
                        + "child component beneath the click. "
                        + "Try clicking anywhere in this layout.</b>",
                Label.CONTENT_RAW), 0, 0, 4, 0);

        layout.addComponent(new TextField(null, "Click here"));
        layout.addComponent(new Link("Click here", null));

        Select select = new Select(null, Arrays.asList("Click here"));
        select.select("Click here");
        layout.addComponent(select);

        // Listen for layout click events
        layout.addListener(new LayoutClickListener() {
            public void layoutClick(LayoutClickEvent event) {

                // Get the child component which was clicked
                Component child = event.getChildComponent();

                if (child == null) {
                    // Not over any child component
                    getWindow().showNotification(
                            "The click was not over any component.");
                } else {
                    // Over a child component
                    getWindow().showNotification(
                            "The click was over a "
                                    + child.getClass().getCanonicalName());
                }
            }
        });

        return layout;
    }

    private Layout createKeyRegisterClickableLayout() {
        // Create a vertical layout with click events
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("90%");
        layout.setSpacing(true);
        layout.addStyleName("border");
        layout.setMargin(true);

        // Add some components inside the layout
        layout.addComponent(new Label(
                "<b>Layout click events register if control keys are pressed during the click and double clicks. "
                        + "Try clicking anywhere inside this layout while holding CTRL, ALT, SHIFT or META key down.</b>",
                Label.CONTENT_RAW));

        // Listen for layout click events
        layout.addListener(new LayoutClickListener() {
            public void layoutClick(LayoutClickEvent event) {
                StringBuilder message = new StringBuilder();
                if (event.isCtrlKey()) {
                    message.append("CTRL+");
                }
                if (event.isAltKey()) {
                    message.append("ALT+");
                }
                if (event.isShiftKey()) {
                    message.append("SHIFT+");
                }
                if (event.isMetaKey()) {
                    message.append("META+");
                }
                if (event.isDoubleClick()) {
                    message.append("DOUBLE CLICK");
                } else {
                    message.append("CLICK");
                }
                getWindow().showNotification(message.toString());
            }
        });

        return layout;
    }
}
