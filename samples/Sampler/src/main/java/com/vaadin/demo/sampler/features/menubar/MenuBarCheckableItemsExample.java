package com.vaadin.demo.sampler.features.menubar;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public class MenuBarCheckableItemsExample extends VerticalLayout {

    private MenuBar menubar = new MenuBar();

    public MenuBarCheckableItemsExample() {

        final MenuBar.MenuItem file = menubar.addItem("File", null);
        file.addItem("New...", menuCommand);
        file.addItem("Open...", menuCommand);
        file.addSeparator();

        file.addItem("Save", menuCommand);
        file.addSeparator();

        // Save on exit is checkable but we do not want any listener to be
        // called when its state changes
        MenuItem saveOnExit = file.addItem("Save on exit", null);
        saveOnExit.setCheckable(true);
        saveOnExit.setChecked(true);

        file.addSeparator();
        file.addItem("Exit", menuCommand);

        final MenuBar.MenuItem settings = menubar.addItem("Settings", null);

        // These settings are checkable and the listener is called when their
        // state changes
        MenuItem setting1 = settings.addItem(
                "Allow settings to be changed by all users", menuCommand);
        setting1.setCheckable(true);
        setting1.setChecked(true);

        MenuItem setting2 = settings.addItem("Convert XML files automatically",
                menuCommand);
        setting2.setCheckable(true);

        MenuItem setting3 = settings.addItem("Convert files automatically",
                menuCommand);
        setting3.setCheckable(true);

        settings.addSeparator();
        // This could be used to show a popup with all the settings for the
        // application
        settings.addItem("More settings...", menuCommand);

        addComponent(menubar);
    }

    private Command menuCommand = new Command() {
        public void menuSelected(MenuItem selectedItem) {
            if (selectedItem.isCheckable()) {
                getWindow().showNotification(
                        "'" + selectedItem.getText() + "' was set to "
                                + selectedItem.isChecked());
            } else {
                getWindow().showNotification(
                        "Non-selectable item '" + selectedItem.getText()
                                + "' was clicked");
            }
        }
    };

}
