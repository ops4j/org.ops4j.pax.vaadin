package com.vaadin.demo.sampler.features.commons;

import java.util.TimeZone;

import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

public class BrowserInformationExample extends VerticalLayout {

    private boolean populated = false;

    public BrowserInformationExample() {
        // We use the attach method in this example because getApplication()
        // will return null until the example is attached to the application. In
        // an application you would typically have an application reference to
        // use.
    }

    @Override
    public void attach() {
        if (populated) {
            // Only populate the layout once
            return;
        }

        // Find the context we are running in and get the browser information
        // from that.
        WebApplicationContext context = ((WebApplicationContext) getApplication()
                .getContext());
        WebBrowser webBrowser = context.getBrowser();

        // Create a text to show based on the browser.
        String browserText = getBrowserAndVersion(webBrowser);
        browserText = browserText + " in " + getOperatingSystem(webBrowser);

        // Create labels for the information and add them to the application
        Label ipAddresslabel = new Label("Hello user from <b>"
                + webBrowser.getAddress() + "</b>.", Label.CONTENT_XHTML);
        Label browser = new Label(
                "You are running <b>" + browserText + "</b>.",
                Label.CONTENT_XHTML);
        Label screenSize = new Label("Your screen resolution is <b>"
                + webBrowser.getScreenWidth() + "x"
                + webBrowser.getScreenHeight() + "</b>.", Label.CONTENT_XHTML);
        Label locale = new Label("Your browser is set to primarily use the <b>"
                + webBrowser.getLocale() + "</b> locale.", Label.CONTENT_XHTML);

        // Client timezone offset w/o possible DST:
        int rtzOffset = webBrowser.getRawTimezoneOffset();
        // DST:
        int dst = webBrowser.getDSTSavings();
        // use raw offset to get possible TZ:
        String[] tzs = TimeZone.getAvailableIDs(rtzOffset);
        NativeSelect timeZones = new NativeSelect();
        for (String id : tzs) {
            TimeZone tz = TimeZone.getTimeZone(id);
            if (dst == tz.getDSTSavings()) {
                // only include zones w/ DST if we know we have DST
                String caption = id + " (" + tz.getDisplayName() + ")";
                timeZones.addItem(caption);
                if (timeZones.getValue() == null) {
                    // select first
                    timeZones.setValue(caption);
                }
            }
        }
        timeZones.setImmediate(true);
        timeZones.setNullSelectionAllowed(false);
        timeZones.setCaption(getTimeZoneInfoString(webBrowser));

        addComponent(ipAddresslabel);
        addComponent(browser);
        addComponent(screenSize);
        addComponent(locale);
        addComponent(timeZones);

        populated = true;
    }

    private String getTimeZoneInfoString(WebBrowser webBrowser) {
        // Client timezone offset:
        int tzOffset = webBrowser.getTimezoneOffset();
        String infoStr = String.format("Your browser indicates GMT%s%d",
                (tzOffset < 0 ? "-" : "+"), Math.abs(tzoToHours(tzOffset)));
        if (webBrowser.isDSTInEffect()) {
            infoStr += String.format(" and DST %d",
                    tzoToHours(webBrowser.getDSTSavings()));
        }
        return infoStr + ", which could mean:";
    }

    private static int tzoToHours(int ms) {
        return ms / 1000 / 60 / 60;
    }

    private String getOperatingSystem(WebBrowser webBrowser) {
        if (webBrowser.isWindows()) {
            return "Windows";
        } else if (webBrowser.isMacOSX()) {
            return "Mac OSX";
        } else if (webBrowser.isLinux()) {
            return "Linux";
        } else {
            return "an unknown operating system";
        }
    }

    private String getBrowserAndVersion(WebBrowser webBrowser) {
        if (webBrowser.isChrome()) {
            return "Chrome " + webBrowser.getBrowserMajorVersion() + "."
                    + webBrowser.getBrowserMinorVersion();
        } else if (webBrowser.isOpera()) {
            return "Opera " + webBrowser.getBrowserMajorVersion() + "."
                    + webBrowser.getBrowserMinorVersion();
        } else if (webBrowser.isFirefox()) {
            return "Firefox " + webBrowser.getBrowserMajorVersion() + "."
                    + webBrowser.getBrowserMinorVersion();
        } else if (webBrowser.isSafari()) {
            return "Safari " + webBrowser.getBrowserMajorVersion() + "."
                    + webBrowser.getBrowserMinorVersion();
        } else if (webBrowser.isIE()) {
            return "Internet Explorer " + webBrowser.getBrowserMajorVersion();
        } else {
            return "an unknown browser";
        }
    }
}
