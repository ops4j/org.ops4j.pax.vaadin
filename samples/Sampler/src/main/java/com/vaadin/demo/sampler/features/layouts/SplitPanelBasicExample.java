package com.vaadin.demo.sampler.features.layouts;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class SplitPanelBasicExample extends VerticalLayout {

    public static final String brownFox = "The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. ";

    public SplitPanelBasicExample() {
        // First a vertical SplitPanel
        final VerticalSplitPanel vert = new VerticalSplitPanel();
        vert.setHeight("450px");
        vert.setWidth("100%");
        vert.setSplitPosition(150, Sizeable.UNITS_PIXELS);
        addComponent(vert);

        // add a label to the upper area
        vert.addComponent(new Label(brownFox));

        // Add a horizontal SplitPanel to the lower area
        final HorizontalSplitPanel horiz = new HorizontalSplitPanel();
        horiz.setSplitPosition(50); // percent
        vert.addComponent(horiz);

        // left component:
        horiz.addComponent(new Label(brownFox));

        // right component:
        horiz.addComponent(new Label(brownFox));

        // Lock toggle button
        CheckBox toggleLocked = new CheckBox("Splits locked",
                new Button.ClickListener() {
                    // inline click.listener
                    public void buttonClick(ClickEvent event) {
                        vert.setLocked(event.getButton().booleanValue());
                        horiz.setLocked(event.getButton().booleanValue());
                    }
                });
        toggleLocked.setImmediate(true);
        addComponent(toggleLocked);

    }
}