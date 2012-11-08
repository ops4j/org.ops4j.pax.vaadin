package com.vaadin.demo.sampler.features.commons;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class PackageIconsExample extends VerticalLayout {

    private String[] icons = new String[] { "arrow-down.png", "arrow-left.png",
            "arrow-right.png", "arrow-up.png", "attention.png", "calendar.png",
            "cancel.png", "document.png", "document-add.png",
            "document-delete.png", "document-doc.png", "document-image.png",
            "document-pdf.png", "document-ppt.png", "document-txt.png",
            "document-web.png", "document-xsl.png", "email.png",
            "email-reply.png", "email-send.png", "folder.png",
            "folder-add.png", "folder-delete.png", "globe.png", "help.png",
            "lock.png", "note.png", "ok.png", "reload.png", "settings.png",
            "trash.png", "trash-full.png", "user.png", "users.png" };

    private String[] sizes = new String[] { "16", "32", "64" };

    public PackageIconsExample() {
        setSpacing(true);

        TabSheet tabSheet = new TabSheet();
        tabSheet.setStyleName(Reindeer.TABSHEET_MINIMAL);

        for (String size : sizes) {
            int iconsSideBySide = size.equals("64") ? 2 : 3;
            GridLayout grid = new GridLayout(iconsSideBySide * 2, 1);
            grid.setSpacing(true);
            grid.setMargin(true);
            tabSheet.addTab(grid, size + "x" + size);

            tabSheet.addComponent(grid);
            for (String icon : icons) {
                Resource res = new ThemeResource("../runo/icons/" + size + "/"
                        + icon);

                Embedded e = new Embedded(null, res);

                // Set size to avoid flickering when loading
                e.setWidth(size + "px");
                e.setHeight(size + "px");

                Label name = new Label(icon);
                if (size.equals("64")) {
                    name.setWidth("185px");
                } else {
                    name.setWidth("150px");
                }

                grid.addComponent(e);
                grid.addComponent(name);

                grid.setComponentAlignment(name, Alignment.MIDDLE_LEFT);
            }
        }

        addComponent(tabSheet);
    }
}
