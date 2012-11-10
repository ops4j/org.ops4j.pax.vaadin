package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public class MenuBarTooltipsExample extends VerticalLayout {

    private MenuBar menubar = new MenuBar();

    public MenuBarTooltipsExample() {

        // Add tooltip to the menubar itself
        menubar.setDescription("Perform some actions by selecting them from the menus");

        // Save reference to individual items so we can add sub-menu items to
        // them
        final MenuBar.MenuItem file = menubar.addItem("File", null);
        file.setDescription("File menu");

        final MenuBar.MenuItem newItem = file.addItem("New", null);
        newItem.setDescription("Add a new..");

        MenuBar.MenuItem open = file.addItem("Open file...", menuCommand);
        open.setDescription("Retrieve a file from the filesystem");

        file.addSeparator();

        MenuBar.MenuItem item;
        item = newItem.addItem("File", menuCommand);
        item.setDescription("Open a file");

        item = newItem.addItem("Folder", menuCommand);
        item.setDescription("Open a folder");

        item = newItem.addItem("Project...", menuCommand);
        item.setDescription("Open a project");

        item = file.addItem("Close", menuCommand);
        item.setDescription("Closes the selected file");

        item = file.addItem("Close All", menuCommand);
        item.setDescription("Closes all files");

        file.addSeparator();

        item = file.addItem("Save", menuCommand);
        item.setDescription("Saves the file");

        item = file.addItem("Save As...", menuCommand);
        item.setDescription("Saves the file with a different name");

        item = file.addItem("Save All", menuCommand);
        item.setDescription("Saves all files");

        final MenuBar.MenuItem edit = menubar.addItem("Edit", null);
        edit.setDescription("Edit menu");

        item = edit.addItem("Undo", menuCommand);
        item.setDescription("Reverses recent changes");

        item = edit.addItem("Redo", menuCommand);
        item.setEnabled(false);
        item.setDescription("Redoes undone changed");

        edit.addSeparator();

        item = edit.addItem("Cut", menuCommand);
        item.setDescription("Copies the text to the clipboard and removes it");

        item = edit.addItem("Copy", menuCommand);
        item.setDescription("Copies the text to the clipboard");

        item = edit.addItem("Paste", menuCommand);
        item.setDescription("Copies the contents of the clipboard on to the document");

        edit.addSeparator();

        final MenuBar.MenuItem find = edit.addItem("Find/Replace", menuCommand);
        find.setDescription("Find or Replace text");

        // Actions can be added inline as well, of course
        item = find.addItem("Google Search", new Command() {
            public void menuSelected(MenuItem selectedItem) {
                getWindow().open(new ExternalResource("http://www.google.com"));
            }
        });

        item.setDescription("Search with Google");

        find.addSeparator();

        item = find.addItem("Find/Replace...", menuCommand);
        item.setDescription("Finds or replaces text");

        item = find.addItem("Find Next", menuCommand);
        item.setDescription("Find the next occurrence");

        item = find.addItem("Find Previous", menuCommand);
        item.setDescription("Find the previous occurrence");

        final MenuBar.MenuItem view = menubar.addItem("View", null);
        view.setDescription("View menu");

        item = view.addItem("Show/Hide Status Bar", menuCommand);
        item.setDescription("Toggles the visibility of the Status Bar");

        item = view.addItem("Customize Toolbar...", menuCommand);
        item.setDescription("Add or remove items in the toolbar");

        view.addSeparator();

        item = view.addItem("Actual Size", menuCommand);
        item.setDescription("Resize view to the original size");

        item = view.addItem("Zoom In", menuCommand);
        item.setDescription("Zoom the document in by 10%");

        item = view.addItem("Zoom Out", menuCommand);
        item.setDescription("Zoom the doucment out by 10%");

        addComponent(menubar);
    }

    private Command menuCommand = new Command() {
        public void menuSelected(MenuItem selectedItem) {
            getWindow().showNotification("Action " + selectedItem.getText());
        }
    };
}
