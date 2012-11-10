package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public class MenuBarHtmlItemsExample extends VerticalLayout {
    public MenuBarHtmlItemsExample() {
        final CheckBox htmlAllowedBox = new CheckBox("HTML content allowed",
                false);
        htmlAllowedBox.setImmediate(true);

        addComponent(htmlAllowedBox);

        final MenuBar menubar = new MenuBar();

        htmlAllowedBox.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Boolean htmlAllowed = (Boolean) htmlAllowedBox.getValue();
                menubar.setHtmlContentAllowed(htmlAllowed.booleanValue());
            }
        });

        // Save reference to individual items so we can add sub-menu items to
        // them
        final MenuBar.MenuItem file = menubar.addItem("<u>File</u>", null);
        final MenuBar.MenuItem newItem = file.addItem("New", null);
        file.addItem("<b>Open</b> file...", null);
        file.addSeparator();

        newItem.addItem("File", null);
        newItem.addItem("<i>Folder</i>", null);
        newItem.addItem("Project...", null);

        file.addItem("Close", null);
        file.addItem("Close All", null);
        file.addSeparator();

        file.addItem("Save", null);
        file.addItem("Save As...", null);
        file.addItem("Save All", null);

        final MenuBar.MenuItem edit = menubar.addItem("<s>Edit</s>", null);
        edit.addItem("Undo", null);
        edit.addItem("Redo", null).setEnabled(false);
        edit.addSeparator();

        edit.addItem("Cut", null);
        edit.addItem("Copy", null);
        edit.addItem("Paste", null);
        edit.addSeparator();

        final MenuBar.MenuItem find = edit.addItem("Find/Replace", null);

        // Actions can be added inline as well, of course
        find.addItem("Google Search", new Command() {
            public void menuSelected(MenuItem selectedItem) {
                getWindow().open(new ExternalResource("http://www.google.com"));
            }
        });
        find.addSeparator();
        find.addItem("Find/Replace...", null);
        find.addItem("Find Next", null);
        find.addItem("Find Previous", null);

        final MenuBar.MenuItem view = menubar.addItem("View", null);
        view.addItem("Show/Hide Status Bar", null);
        view.addItem("Customize Toolbar...", null);
        view.addSeparator();

        view.addItem("Actual Size", null);
        view.addItem("Zoom In", null);
        view.addItem("Zoom Out", null);

        addComponent(menubar);
    }
}
