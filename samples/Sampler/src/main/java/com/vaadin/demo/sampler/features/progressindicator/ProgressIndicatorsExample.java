package com.vaadin.demo.sampler.features.progressindicator;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProgressIndicatorsExample extends VerticalLayout {

    private ProgressIndicator pi1;
    private ProgressIndicator pi2;

    private Worker1 worker1;
    private Worker2 worker2;

    private Button startButton1;
    private Button startButton2;

    public ProgressIndicatorsExample() {

        setSpacing(true);

        addComponent(new Label(
                "<strong>Normal mode</strong> Runs for 20 seconds",
                Label.CONTENT_XHTML));

        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        addComponent(hl);

        // Add a normal progress indicator
        pi1 = new ProgressIndicator();
        pi1.setIndeterminate(false);
        pi1.setEnabled(false);
        hl.addComponent(pi1);
        hl.setComponentAlignment(pi1, Alignment.MIDDLE_LEFT);

        startButton1 = new Button("Start normal", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                worker1 = new Worker1();
                worker1.start();
                pi1.setEnabled(true);
                pi1.setValue(0f);
                startButton1.setEnabled(false);
            }
        });
        startButton1.setStyleName("small");
        hl.addComponent(startButton1);

        addComponent(new Label(
                "<strong>Indeterminate mode</strong> Runs for 10 seconds",
                Label.CONTENT_XHTML));

        hl = new HorizontalLayout();
        hl.setSpacing(true);
        addComponent(hl);

        // Add an indeterminate progress indicator
        pi2 = new ProgressIndicator();
        pi2.setIndeterminate(true);
        pi2.setPollingInterval(5000);
        pi2.setEnabled(false);
        hl.addComponent(pi2);

        startButton2 = new Button("Start indeterminate",
                new Button.ClickListener() {

                    public void buttonClick(ClickEvent event) {
                        worker2 = new Worker2();
                        worker2.start();
                        pi2.setEnabled(true);
                        pi2.setVisible(true);
                        startButton2.setEnabled(false);
                    }
                });

        startButton2.setStyleName("small");
        hl.addComponent(startButton2);

    }

    public void prosessed() {
        int i = worker1.getCurrent();
        if (i == Worker1.MAX) {
            pi1.setEnabled(false);
            startButton1.setEnabled(true);
            pi1.setValue(1f);
        } else {
            pi1.setValue((float) i / Worker1.MAX);
        }
    }

    public void prosessed2() {
        pi2.setEnabled(false);
        startButton2.setEnabled(true);
    }

    public class Worker1 extends Thread {
        int current = 1;
        public final static int MAX = 20;

        @Override
        public void run() {
            for (; current <= MAX; current++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // All modifications to Vaadin components should be synchronized
                // over application instance. For normal requests this is done
                // by the servlet. Here we are changing the application state
                // via a separate thread.
                synchronized (getApplication()) {
                    prosessed();
                }
            }
        }

        public int getCurrent() {
            return current;
        }

    }

    public class Worker2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // synchronize changes over application
            synchronized (getApplication()) {
                prosessed2();
            }
        }
    }
}
