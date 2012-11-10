package com.vaadin.demo.sampler.features.text;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TextFieldSecretExample extends VerticalLayout {

    private final TextField username;
    private final PasswordField password;

    public TextFieldSecretExample() {
        setSizeUndefined(); // let content 'push' size
        setSpacing(true);

        // Username
        username = new TextField("Username");
        addComponent(username);

        // Password
        password = new PasswordField("Password");
        addComponent(password);

        // Login button
        Button loginButton = new Button("Login", new Button.ClickListener() {
            // inline click listener
            public void buttonClick(ClickEvent event) {
                getWindow().showNotification(
                        "User: " + username.getValue() + " Password: "
                                + password.getValue());

            }
        });
        addComponent(loginButton);
        setComponentAlignment(loginButton, Alignment.TOP_RIGHT);

    }
}
