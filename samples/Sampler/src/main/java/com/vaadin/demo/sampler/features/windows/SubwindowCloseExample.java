package com.vaadin.demo.sampler.features.windows;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

@SuppressWarnings("serial")
public class SubwindowCloseExample extends VerticalLayout {

    private static final String openWindowText = "Open a window";
    private static final String closeWindowText = "Close the window";
    Window subwindow;
    private Button openCloseButton;
    private CheckBox closableWindow;

    public SubwindowCloseExample() {

        closableWindow = new CheckBox("Allow user to close the window", true);
        closableWindow.setImmediate(true);
        closableWindow.addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                subwindow.setClosable(closableWindow.booleanValue());
            }

        });

        // Create the window
        subwindow = new Window("A subwindow w/ close-listener");
        subwindow.addListener(new Window.CloseListener() {
            // inline close-listener
            public void windowClose(CloseEvent e) {
                getWindow().showNotification("Window closed by user");
                openCloseButton.setCaption(openWindowText);
            }
        });

        // Configure the windws layout; by default a VerticalLayout
        VerticalLayout layout = (VerticalLayout) subwindow.getContent();
        layout.setMargin(true);
        layout.setSpacing(true);

        // Add some content; a label and a close-button
        Label message = new Label("This is a subwindow with a close-listener.");
        subwindow.addComponent(message);

        // Add a button for opening the subwindow
        openCloseButton = new Button("Open window", new Button.ClickListener() {
            // inline click-listener
            public void buttonClick(ClickEvent event) {
                if (subwindow.getParent() != null) {
                    // window is already showing
                    (subwindow.getParent()).removeWindow(subwindow);
                    openCloseButton.setCaption(openWindowText);
                } else {
                    // Open the subwindow by adding it to the parent window
                    getWindow().addWindow(subwindow);
                    openCloseButton.setCaption(closeWindowText);
                }
            }
        });
        setSpacing(true);
        addComponent(closableWindow);
        addComponent(openCloseButton);

    }

}