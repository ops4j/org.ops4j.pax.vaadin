package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MenuBarWithIconsExample extends VerticalLayout {

    private MenuBar menubar = new MenuBar();

    public MenuBarWithIconsExample() {

        // Save reference to individual items so we can add sub-menu items to
        // them
        final MenuBar.MenuItem file = menubar.addItem("File", null);
        final MenuBar.MenuItem newItem = file.addItem("New", null);
        file.addItem("Open file...", new ThemeResource(
                "../runo/icons/16/folder.png"), menuCommand);
        file.addSeparator();

        newItem.addItem("File", new ThemeResource(
                "../runo/icons/16/document.png"), menuCommand);
        newItem.addItem("Folder", new ThemeResource(
                "../runo/icons/16/folder.png"), menuCommand);
        newItem.addItem("Project...", new ThemeResource(
                "../runo/icons/16/globe.png"), menuCommand);

        file.addItem("Close", menuCommand);
        file.addItem("Close All", menuCommand);
        file.addSeparator();

        file.addItem("Save", menuCommand);
        file.addItem("Save As...", menuCommand);
        file.addItem("Save All", menuCommand);

        final MenuBar.MenuItem edit = menubar.addItem("Edit", null);
        edit.addItem("Undo", menuCommand);
        edit.addItem("Redo", menuCommand).setEnabled(false);
        edit.addSeparator();

        edit.addItem("Cut", menuCommand);
        edit.addItem("Copy", menuCommand);
        edit.addItem("Paste", menuCommand);
        edit.addSeparator();

        final MenuBar.MenuItem find = edit.addItem("Find/Replace", menuCommand);

        // Actions can be added inline as well, of course
        find.addItem("Google Search", new Command() {
            public void menuSelected(MenuItem selectedItem) {
                getWindow().open(new ExternalResource("http://www.google.com"));
            }
        });
        find.addSeparator();
        find.addItem("Find/Replace...", menuCommand);
        find.addItem("Find Next", menuCommand);
        find.addItem("Find Previous", menuCommand);

        final MenuBar.MenuItem view = menubar.addItem("View",
                new ThemeResource("../runo/icons/16/user.png"), null);
        view.addItem("Show/Hide Status Bar", menuCommand);
        view.addItem("Customize Toolbar...", menuCommand);
        view.addSeparator();

        view.addItem("Actual Size", menuCommand);
        view.addItem("Zoom In", menuCommand);
        view.addItem("Zoom Out", menuCommand);

        addComponent(menubar);
    }

    private Command menuCommand = new Command() {
        public void menuSelected(MenuItem selectedItem) {
            getWindow().showNotification("Action " + selectedItem.getText());
        }
    };

}
