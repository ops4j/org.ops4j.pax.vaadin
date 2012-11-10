package com.vaadin.demo.sampler.features.form;

import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LoginFormExample extends VerticalLayout {

    public LoginFormExample() {

        LoginForm login = new LoginForm();
        login.setWidth("100%");
        login.setHeight("300px");
        login.addListener(new LoginForm.LoginListener() {
            public void onLogin(LoginEvent event) {
                getWindow().showNotification(
                        "New Login",
                        "Username: " + event.getLoginParameter("username")
                                + ", password: "
                                + event.getLoginParameter("password"));
            }
        });
        addComponent(login);

    }
}
