package com.vaadin.demo.sampler.features.text;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.demo.sampler.ExampleUtil;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TextFieldTextChangeEventExample extends VerticalLayout {

    public TextFieldTextChangeEventExample() {
        final IndexedContainer nameContainer = ExampleUtil.getNameContainer();

        TextField filterField = new TextField("Filter");
        filterField.setTextChangeEventMode(TextChangeEventMode.LAZY);
        filterField.setTextChangeTimeout(200);
        filterField.addListener(new TextChangeListener() {

            public void textChange(TextChangeEvent event) {
                nameContainer.removeAllContainerFilters();
                nameContainer.addContainerFilter(
                        ExampleUtil.PERSON_PROPERTY_NAME, event.getText(),
                        true, false);
            }
        });

        Table table = new Table(null, nameContainer);
        table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);

        setSpacing(false);
        addComponent(filterField);
        addComponent(table);

        filterField.setWidth("150px");
        table.setWidth("150px");
    }
}
