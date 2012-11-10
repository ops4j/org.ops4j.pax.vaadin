package com.vaadin.demo.sampler.features.layouts;

import java.util.Arrays;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class SplitPanelPositioningExample extends VerticalLayout {

    private OptionGroup measurePositionFromLeft;

    private OptionGroup measurePositionFromTop;

    private HorizontalSplitPanel horizontalSplitPanel;

    private VerticalSplitPanel verticalSplitPanel;

    public SplitPanelPositioningExample() {
        setStyleName("split-panel-positioning-example");
        setSpacing(true);

        HorizontalLayout controls = new HorizontalLayout();
        controls.setSpacing(true);
        addComponent(controls);

        verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setSplitPosition(100, Sizeable.UNITS_PIXELS);
        verticalSplitPanel.setLocked(true);
        verticalSplitPanel.setHeight("450px");
        verticalSplitPanel.setWidth("100%");
        addComponent(verticalSplitPanel);

        // Add some content to the top
        final Label topArea = new Label();
        topArea.setStyleName("top-area");
        topArea.addStyleName("measured-from-top");
        topArea.setSizeFull();
        verticalSplitPanel.addComponent(topArea);

        // Add a horizontal split panel in the bottom area
        horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setSplitPosition(30, Sizeable.UNITS_PERCENTAGE);
        horizontalSplitPanel.setSizeFull();
        horizontalSplitPanel.setLocked(true);
        verticalSplitPanel.addComponent(horizontalSplitPanel);

        // Add some content to the left and right sides of the vertical layout
        final Label leftArea = new Label();
        leftArea.setStyleName("left-area");
        leftArea.addStyleName("measured-from-left");
        leftArea.setSizeFull();
        horizontalSplitPanel.addComponent(leftArea);

        final Label rightArea = new Label();
        rightArea.setStyleName("right-area");
        rightArea.setSizeFull();
        horizontalSplitPanel.addComponent(rightArea);

        // Allow user to set the splitter positioning
        measurePositionFromLeft = new OptionGroup(
                "Horizontal split position",
                Arrays.asList("30% from left", "30% from right"));
        measurePositionFromLeft.setValue("30% from left");
        measurePositionFromLeft.setImmediate(true);
        measurePositionFromLeft.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {

                if (event.getProperty().getValue().equals("30% from right")) {

                    // Measure 30% from the left
                    leftArea.removeStyleName("measured-from-left");
                    rightArea.removeStyleName("measured-from-bottom");
                    rightArea.addStyleName("measured-from-right");
                    horizontalSplitPanel.setSplitPosition(30,
                            Sizeable.UNITS_PERCENTAGE, true);
               } else {

                    // Measure 30% from right
                    rightArea.removeStyleName("measured-from-right");
                    leftArea.removeStyleName("measured-from-bottom");
                    leftArea.addStyleName("measured-from-left");
                    horizontalSplitPanel.setSplitPosition(30,
                            Sizeable.UNITS_PERCENTAGE, false);
               }
            }
        });
        controls.addComponent(measurePositionFromLeft);
        controls.setComponentAlignment(measurePositionFromLeft,
                Alignment.MIDDLE_CENTER);

        measurePositionFromTop = new OptionGroup("Vertical split position",
                Arrays.asList("100px from top", "100px from bottom"));
        measurePositionFromTop.setValue("100px from top");
        measurePositionFromTop.setImmediate(true);
        measurePositionFromTop.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {

                if (event.getProperty().getValue().equals("100px from bottom")) {

                    // Measure 100px from the bottom
                    topArea.removeStyleName("measured-from-top");

                    if (measurePositionFromLeft.getValue().equals(
                            "30% from left")) {
                        rightArea.addStyleName("measured-from-bottom");
                    } else {
                        leftArea.addStyleName("measured-from-bottom");
                    }

                    verticalSplitPanel.setSplitPosition(100,
                            Sizeable.UNITS_PIXELS, true);

                } else {

                    // Measure 100px from the top
                    if (measurePositionFromLeft.getValue().equals(
                            "30% from left")) {
                        rightArea.removeStyleName("measured-from-bottom");
                    } else {
                        leftArea.removeStyleName("measured-from-bottom");
                    }

                    topArea.addStyleName("measured-from-top");
                    verticalSplitPanel.setSplitPosition(100,
                            Sizeable.UNITS_PIXELS, false);
                }

            }
        });
        controls.addComponent(measurePositionFromTop);
        controls.setComponentAlignment(measurePositionFromTop,
                Alignment.MIDDLE_CENTER);

    }


}
