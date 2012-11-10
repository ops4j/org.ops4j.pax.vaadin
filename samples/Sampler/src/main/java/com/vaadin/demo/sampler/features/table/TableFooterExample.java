package com.vaadin.demo.sampler.features.table;

import java.text.NumberFormat;
import java.text.ParseException;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.demo.sampler.ExampleUtil;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class TableFooterExample extends VerticalLayout {

    public TableFooterExample() {

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

        // Create a table to show the data in
        Table table = new Table("Order table", dataSource);
        table.setPageLength(6);
        table.setWidth("100%");

        // Set alignments
        table.setColumnAlignments(new String[] { Table.ALIGN_LEFT,
                Table.ALIGN_RIGHT, Table.ALIGN_RIGHT, Table.ALIGN_RIGHT });

        // Set column widths
        table.setColumnExpandRatio(ExampleUtil.ORDER_DESCRIPTION_PROPERTY_ID, 1);

        // Enable footer
        table.setFooterVisible(true);

        // Add some total sum and description to footer
        table.setColumnFooter(ExampleUtil.ORDER_DESCRIPTION_PROPERTY_ID,
                "Total Price");
        table.setColumnFooter(ExampleUtil.ORDER_ITEMPRICE_PROPERTY_ID,
                NumberFormat.getCurrencyInstance().format(totalSum));

        addComponent(table);
    }
}
