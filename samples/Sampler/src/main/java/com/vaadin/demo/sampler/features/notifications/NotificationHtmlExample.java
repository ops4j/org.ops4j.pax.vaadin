package com.vaadin.demo.sampler.features.notifications;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class NotificationHtmlExample extends VerticalLayout {
    final TextArea notificationCaption = new TextArea("Notification caption",
            "<b>Caption \ncontaining a line break (\\n)</b>");
    final TextArea notificationDescription = new TextArea(
            "Notification description",
            "<i>Content <br />containing a HTML line break (&lt;br&nbsp/&gt;)"
                    + " and HTML entities</i>");

    public NotificationHtmlExample() {
        setSpacing(true);

        addComponent(notificationCaption);

        addComponent(notificationDescription);

        addComponent(new Button("Show as html", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                showNotification(true);
            }
        }));

        addComponent(new Button("Show as plain text",
                new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        showNotification(false);
                    }
                }));
    }

    private void showNotification(boolean htmlAllowed) {
        getWindow().showNotification((String) notificationCaption.getValue(),
                (String) notificationDescription.getValue(),
                Notification.TYPE_HUMANIZED_MESSAGE, htmlAllowed);
    }

}
