/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pax.vaadin.samples.simple.blueprint.app;

import java.util.Date;

import com.vaadin.Application;
import com.vaadin.terminal.Terminal;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application
{
    private Window window;
    
    @Override
    public void init() {
        window = new Window("My Vaadin Application");
        setMainWindow(window);

        // Click & Fails Me buttons
        Button clickButton = new Button("Click Me");
        Button failsButton = new Button ("Fail Me");

        // Notification displayed when click button is called
        final Window.Notification notif = new Window.Notification(
                "The time is " + new Date(),
                Window.Notification.TYPE_WARNING_MESSAGE);
        // Notification position.
        notif.setPosition(Window.Notification.POSITION_CENTERED_BOTTOM);

        // Add a listener on Click button
        clickButton.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               window.addComponent(new Label("Thank you for clicking"));
               window.showNotification(notif);
            }
        });

        // Add a listener for Fails button
        failsButton.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                // Throw some exception.
                throw new RuntimeException("You can't catch this.");
            }
        });

        window.addComponent(clickButton);
        window.addComponent(failsButton);
        
    }

    @Override
    public void terminalError(Terminal.ErrorEvent event) {

        // Call the default implementation.
        super.terminalError(event);

        // Some custom behaviour.
        if (getMainWindow() != null) {
            getMainWindow().showNotification(
                    "An unchecked exception occured!",
                    event.getThrowable().toString(),
                    Window.Notification.TYPE_ERROR_MESSAGE);
        }
    }
    
}
