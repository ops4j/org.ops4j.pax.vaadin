package com.vaadin.demo.sampler.features.tabsheets;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TabSheetClosingExample extends VerticalLayout implements
        TabSheet.SelectedTabChangeListener, TabSheet.CloseHandler {

    private TabSheet t;

    public TabSheetClosingExample() {
        // Tab 1 content
        VerticalLayout l1 = new VerticalLayout();
        l1.setMargin(true);
        l1.addComponent(new Label("There are no previously saved actions."));
        // Tab 2 content
        VerticalLayout l2 = new VerticalLayout();
        l2.setMargin(true);
        l2.addComponent(new Label("There are no saved notes."));
        // Tab 3 content
        VerticalLayout l3 = new VerticalLayout();
        l3.setMargin(true);
        l3.addComponent(new Label("There are currently no issues."));
        // Tab 4 content
        VerticalLayout l4 = new VerticalLayout();
        l4.setMargin(true);
        l4.addComponent(new Label("There are no comments."));
        // Tab 5 content
        VerticalLayout l5 = new VerticalLayout();
        l5.setMargin(true);
        l5.addComponent(new Label("There is no new feedback."));

        t = new TabSheet();
        t.setHeight("200px");
        t.setWidth("400px");

        final Tab saved = t.addTab(l1, "Saved actions");
        saved.setClosable(true);
        final Tab notes = t.addTab(l2, "Notes");
        notes.setClosable(true);
        final Tab issues = t.addTab(l3, "Issues");
        issues.setClosable(true);
        final Tab comments = t.addTab(l4, "Comments");
        comments.setClosable(true);
        final Tab feedback = t.addTab(l5, "Feedback");
        feedback.setClosable(true);

        t.addListener(this);
        t.setCloseHandler(this);

        addComponent(t);
    }

    public void selectedTabChange(SelectedTabChangeEvent event) {
        TabSheet tabsheet = event.getTabSheet();
        Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
        if (tab != null) {
            getWindow().showNotification("Selected tab: " + tab.getCaption());
        }
    }

    public void onTabClose(TabSheet tabsheet, Component tabContent) {
        getWindow().showNotification(
                "Closed tab: " + tabsheet.getTab(tabContent).getCaption());
        tabsheet.removeComponent(tabContent);
    }
}
