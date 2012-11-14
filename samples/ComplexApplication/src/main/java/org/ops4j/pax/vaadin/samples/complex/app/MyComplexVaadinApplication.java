/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.ops4j.pax.vaadin.samples.complex.app;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.util.Date;

/**
 * A more complex Vaadin application example
 */
public class MyComplexVaadinApplication extends Application {

    private Window window;

    @Override
    public void init() {

        setTheme("table");

        window = new Window("My Complex Vaadin Application");
        setMainWindow(window);

        final VerticalLayout layout = new VerticalLayout();
        final Table table = new Table("The Pax projects");
        table.addStyleName("multirowlabels");

        // Define two columns for the built-in container
        table.addContainerProperty("Code", String.class, null);
        table.addContainerProperty("Description", String.class, null);
        table.addContainerProperty("Creation Date", Date.class, null);
        // table.addContainerProperty("Edit", CheckBox.class, null);

        // Add a row the hard way
        Object newItemId = table.addItem();
        Item row1 = table.getItem(newItemId);
        row1.getItemProperty("Code").setValue("pax-vaadin");
        row1.getItemProperty("Description").setValue("OSGI vaadin project");
        //row1.getItemProperty("Creation date").setValue(new Date());

        // Add a few other rows using shorthand addItem()
        table.addItem(new Object[]{"pax-cdi", "pax cdi project", new Date()}, 2);
        table.addItem(new Object[]{"pax-logging", "pax logging project", new Date()}, 3);
        table.addItem(new Object[]{"pax-runner", "osgi runner tool", new Date()}, 4);
        table.addItem(new Object[]{"pax-web", "pax web project", new Date()}, 5);
        table.addItem(new Object[]{"pax-jdbc", "pax jdbc project", new Date()}, 6);

        // Allow selecting
        table.setSelectable(true);

        // Put the table in editable mode
        table.setEditable(false);

        // Reordering
        table.setColumnReorderingAllowed(true);

        // Allow switching to non-editable mode
        final CheckBox editable = new CheckBox("Table is editable", false);
        editable.addListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 6291942958587745232L;

            public void valueChange(ValueChangeEvent event) {
                table.setEditable((Boolean) editable.getValue());
            }
        });
        editable.setImmediate(true);
        layout.addComponent(editable);

        // Handle selection changes
        table.addListener(new Property.ValueChangeListener()
        {
            public void valueChange(ValueChangeEvent event)
            {
                if (event.getProperty().getValue() != null)
                {
                    layout.addComponent(new Label("Selected item id " +
                            event.getProperty().getValue().toString()));
                }
                else // Item deselected
                {
                    layout.addComponent(new Label("Nothing selected"));
                }
            }
        });


        // Set Table Size
        table.setPageLength(table.size());

        layout.addComponent(table);
        window.addComponent(layout);

    }
}
