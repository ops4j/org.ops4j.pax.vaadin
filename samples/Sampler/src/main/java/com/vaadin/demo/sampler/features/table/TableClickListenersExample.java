package com.vaadin.demo.sampler.features.table;

import java.text.NumberFormat;
import java.text.ParseException;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.demo.sampler.ExampleUtil;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.FooterClickEvent;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class TableClickListenersExample extends VerticalLayout {

    public TableClickListenersExample() {
        super();

        // Create our data source
        IndexedContainer dataSource = ExampleUtil.getOrderContainer();

        // Calculate total sum
        double totalSum = 0.0;
        for (int i = 0; i < dataSource.size(); i++) {
            Item item = dataSource.getItem(dataSource.getIdByIndex(i));
            Object value = item.getItemProperty(
                    ExampleUtil.ORDER_ITEMPRICE_PROPERTY_ID).getValue();
            try {
                Number amount = NumberFormat.getCurrencyInstance().parse(
                        value.toString());
                totalSum += amount.doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Create table
        Table table = new Table("", ExampleUtil.getOrderContainer());
        table.setColumnExpandRatio(ExampleUtil.ORDER_DESCRIPTION_PROPERTY_ID, 1);
        table.setSortDisabled(true);
        table.setWidth("100%");
        table.setPageLength(6);
        table.setFooterVisible(true);
        table.setImmediate(true);

        // Add some total sum and description to footer
        table.setColumnFooter(ExampleUtil.ORDER_DESCRIPTION_PROPERTY_ID,
                "Total Price");
        table.setColumnFooter(ExampleUtil.ORDER_ITEMPRICE_PROPERTY_ID,
                NumberFormat.getCurrencyInstance().format(totalSum));

        // Add a header click handler
        table.addListener(new Table.HeaderClickListener() {
            public void headerClick(HeaderClickEvent event) {
                /*
                 * Show a notification help text when the user clicks on a
                 * column header
                 */
                showHeaderHelpText(event.getPropertyId());
            }
        });

        // Add a footer click handler
        table.addListener(new Table.FooterClickListener() {
            public void footerClick(FooterClickEvent event) {
                /*
                 * Show a notification help text when the user clicks on a
                 * column footer
                 */
                showFooterHelpText(event.getPropertyId());
            }
        });

        addComponent(table);
    }

    /**
     * Shows some help text when clicking on the header
     * 
     * @param column
     */
    private void showHeaderHelpText(Object column) {

        Notification notification = null;

        // Description
        if (column == ExampleUtil.ORDER_DESCRIPTION_PROPERTY_ID) {
            notification = new Notification(String.valueOf(column) + "<br>",
                    "The description describes the type of product that has been ordered.");

            // Item price
        } else if (column == ExampleUtil.ORDER_ITEMPRICE_PROPERTY_ID) {
            notification = new Notification(String.valueOf(column) + "<br>",
                    "The item price is calculated by multiplying the unit price with the quantity.");

            // Quantity
        } else if (column == ExampleUtil.ORDER_QUANTITY_PROPERTY_ID) {
            notification = new Notification(String.valueOf(column) + "<br>",
                    "The quantity describes how many items has been ordered.");

            // Unit price
        } else if (column == ExampleUtil.ORDER_UNITPRICE_PROPERTY_ID) {
            notification = new Notification(String.valueOf(column) + "<br>",
                    "The unit price is how much a single items costs. Taxes included.");

        } else {
            return;
        }

        getWindow().showNotification(notification);
    }

    /**
     * Shows a footer help text
     * 
     * @param column
     */
    private void showFooterHelpText(Object column) {
        Notification notification = new Notification("Total Price<br>",
                "The total price is calculated by summing every items item price together.");
        getWindow().showNotification(notification);
    }

}
