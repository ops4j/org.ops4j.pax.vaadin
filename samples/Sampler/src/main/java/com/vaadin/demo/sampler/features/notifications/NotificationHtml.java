package com.vaadin.demo.sampler.features.notifications;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Window.Notification;

public class NotificationHtml extends Feature {

    @Override
    public String getName() {
        return "Html in notifications";
    }

    @Override
    public String getDescription() {
        return "<p>Notifications do by default show the caption and"
                + " description as HTML, which could cause e&#046;g&#046; a"
                + " Cross-site scripting vulnerability if the content of the"
                + " notification can be modified by a malicious user.</p>"
                + "<p>To avoid problems with content entered by untrusted"
                + " users, <i>allowHtmlContent</i> can also set to false,"
                + " causing the caption and description to be displayed as"
                + " plain text.</p>"
                + "<p>Please note that in Notifications using HTML,"
                + " &lt;br&nbsp/&gt; is used to get a line break."
                + " Notifications using plain text gets line break when"
                + " there's a linebreaak (\\n) in the content.</p>";
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new NamedExternalResource(
                "Cross-site scripting",
                "http://en.wikipedia.org/wiki/Cross-site_scripting") };
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Notification.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { NotificationHumanized.class,
                NotificationTray.class, NotificationWarning.class,
                NotificationCustom.class, NotificationError.class };
    }

    @Override
    public Version getSinceVersion() {
        return Version.V67;
    }

}
