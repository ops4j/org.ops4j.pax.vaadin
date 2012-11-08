package com.vaadin.demo.sampler.features.dates;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.demo.sampler.ExampleUtil;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.VerticalLayout;

public class DateTimeZoneExample extends VerticalLayout {
    private InlineDateField datetime;
    private ComboBox timeZoneSelection;

    public DateTimeZoneExample() {
        setSpacing(true);

        datetime = new InlineDateField("Please select the starting time:");

        // Set the value of the PopupDateField to current date
        datetime.setValue(new Date());

        // Set the correct resolution
        datetime.setResolution(InlineDateField.RESOLUTION_MIN);
        datetime.setImmediate(true);
        datetime.setShowISOWeekNumbers(true);
        datetime.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                Date value = (Date) datetime.getValue();
                DateFormat format = DateFormat.getDateTimeInstance(
                        DateFormat.SHORT, DateFormat.FULL);
                getWindow().showNotification(
                        "Date changed to " + format.format(value));
            }
        });

        // Create selection and fill it with time zones
        timeZoneSelection = new ComboBox("Select time zone:");
        timeZoneSelection.setImmediate(true);
        timeZoneSelection.setContainerDataSource(ExampleUtil
                .getTimeZoneContainer());
        timeZoneSelection.setNullSelectionAllowed(false);
        // Select the first item from the container
        timeZoneSelection.setValue(timeZoneSelection.getItemIds().iterator()
                .next());
        timeZoneSelection.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                datetime.setTimeZone(getSelectedTimeZone());
            }
        });
        datetime.setTimeZone(getSelectedTimeZone());

        addComponent(datetime);
        addComponent(timeZoneSelection);
    }

    private TimeZone getSelectedTimeZone() {
        Item selected = timeZoneSelection.getItem(timeZoneSelection.getValue());
        TimeZone value = (TimeZone) selected.getItemProperty(
                ExampleUtil.timezone_PROPERTY_TIME_ZONE).getValue();
        return value;
    }

}
