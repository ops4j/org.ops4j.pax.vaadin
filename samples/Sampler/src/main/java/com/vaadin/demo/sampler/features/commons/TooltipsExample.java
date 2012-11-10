package com.vaadin.demo.sampler.features.commons;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class TooltipsExample extends VerticalLayout {

    private static final String editTxt = "Edit tooltip";
    private static final String applyTxt = "Apply";
    private Button rich;
    private Button plain;

    public TooltipsExample() {
        setSpacing(true);

        plain = new Button("Mouse over for plain tooltip");
        plain.setStyleName(BaseTheme.BUTTON_LINK);
        // add the tooltip:
        plain.setDescription("A simple plaintext tooltip");
        addComponent(plain);

        rich = new Button("Mouse over for richtext tooltip");
        rich.setStyleName(BaseTheme.BUTTON_LINK);
        // The tooltip is added in attach() below because the application url is
        // not available at this point
        addComponent(rich);

        /* Edit */
        final RichTextArea rte = new RichTextArea();
        rte.setValue("Click <b>" + editTxt
                + "</b> to edit this tooltip, then <b>" + applyTxt + "</b>");
        rte.setVisible(false); // hide editor initially
        rte.setWidth("100%");
        addComponent(rte);
        Button apply = new Button(editTxt, new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if (rte.isVisible()) {
                    rte.setVisible(false);
                    event.getButton().setDescription((String) rte.getValue());
                    event.getButton().setCaption(editTxt);
                } else {
                    rte.setVisible(true);
                    event.getButton().setCaption(applyTxt);
                }
            }
        });
        apply.setDescription((String) rte.getValue());
        addComponent(apply);

    }

    @Override
    public void attach() {
        super.attach();
        // add the tooltip:
        rich.setDescription("<h2><img src=\""
                + getApplication().getURL()
                + "VAADIN/themes/sampler/icons/comment_yellow.gif\"/>A richtext tooltip</h2>"
                + "<ul>" + "<li>HTML formatting</li><li>Images<br/>"
                + "</li><li>etc...</li></ul>");
    }
}
