package com.vaadin.demo.tools;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.FeatureSet;

public class GenerateSamplerTest {

    public static class VaadinTestBenchTest {
        private static String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">"
                + "<head profile=\"http://selenium-ide.openqa.org/profiles/test-case\">"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
                + "<link rel=\"selenium.base\" href=\"\" />"
                + "<title>#name#</title>"
                + "</head>"
                + "<body>"
                + "<table cellpadding=\"1\" cellspacing=\"1\" border=\"1\">"
                + "<thead>"
                + "<tr><td rowspan=\"1\" colspan=\"3\">#name#</td></tr>"
                + "</thead><tbody>";

        private static String footer = "</tbody></table>" + "</body>"
                + "</html>";

        private String name;

        private List<TestRow> rows = new ArrayList<TestRow>();

        public VaadinTestBenchTest(String name) {
            this.name = name;
        }

        public void addCmd(String cmd, String target, String value) {
            rows.add(new Command(cmd, target, value));
        }

        public class WaitForVaadinCommand extends Command {

            public WaitForVaadinCommand() {
                super("waitForVaadin", "", "");
            }

        }

        public abstract class TestRow {
            public abstract String getHtml();
        }

        public class Comment extends TestRow {

            private String comment;

            public Comment(String comment) {
                this.comment = comment;
            }

            @Override
            public String getHtml() {
                return "<!--" + comment + "-->\n";
            }

        }

        public class Command extends TestRow {
            private String cmd;
            private String target;
            private String value;

            public Command(String cmd, String target, String value) {
                super();
                this.cmd = cmd;
                this.target = target;
                this.value = value;
            }

            @Override
            public String getHtml() {
                return "<tr>\n" + "<td>" + cmd + "</td>\n" + "<td>" + target
                        + "</td>\n" + "<td>" + value + "</td>\n" + "</tr>\n";
            }

        }

        public void addComment(String comment) {
            rows.add(new Comment(comment));

        }

        public void output() {
            System.out.println(header.replace("#name#", name));
            for (TestRow row : rows) {
                System.out.print(row.getHtml());
            }
            System.out.println(footer.replace("#name#", name));

        }
    }

    private static String NEXT_BUTTON = "vaadin=sampler::/VVerticalLayout[0]/ChildComponentContainer[0]/VHorizontalLayout[0]/ChildComponentContainer[5]/VHorizontalLayout[0]/ChildComponentContainer[1]/VNativeButton[0]";
    private static String TOGGLE_TREE = "vaadin=sampler::/VVerticalLayout[0]/ChildComponentContainer[0]/VHorizontalLayout[0]/ChildComponentContainer[4]/VHorizontalLayout[0]/ChildComponentContainer[1]/VNativeButton[0]";
    private static VaadinTestBenchTest test;

    public static void main(String[] args) {
        test = new VaadinTestBenchTest("sampler-all-samples");
        test.addCmd("openAndWait", "/sampler", "");

        test.addComment("Close left side tree menu");
        test.addCmd("mouseClick", TOGGLE_TREE, "22,11");

        test.addComment("Main page screenshot");
        test.addCmd("pause", "5000", "");
        test.addCmd("screenCapture", "", "mainview");

        StringBuilder script = new StringBuilder();
        test.addComment("Scrolling through all samples to load images");
        FeatureSet samplerFeatureSet = FeatureSet.FEATURES;
        writeFeatureSet(script, samplerFeatureSet, false);

        test.addComment("Start over from the main page");
        test.addCmd(
                "mouseClick",
                "vaadin=sampler::/VVerticalLayout[0]/ChildComponentContainer[0]/VHorizontalLayout[0]/ChildComponentContainer[1]/VCustomComponent[0]/VHorizontalLayout[0]/ChildComponentContainer[0]/VActiveLink[0]/domChild[0]/domChild[0]",
                "1,1");

        test.addComment("Scroll through all samples in order and capture screenshots");
        writeFeatureSet(script, samplerFeatureSet, true);

        test.output();

    }

    private static void writeFeatureSet(StringBuilder script,
            FeatureSet featureSet, boolean capture) {
        for (Feature feature : featureSet.getFeatures()) {
            if (feature instanceof FeatureSet) {
                writeFeatureSet(script, (FeatureSet) feature, capture);
            } else {
                writeFeature(script, feature, capture);
            }
        }
    }

    private static void writeFeature(StringBuilder script, Feature feature,
            boolean capture) {
        test.addCmd("mouseClick", NEXT_BUTTON, "1,1");
        if (capture) {
            String id = getId(feature);
            test.addComment(id);
            if (includeScreenshotInTest(id)) {
                if (needsPause(feature)) {
                    test.addCmd("pause", "1000", "");
                }
                if (feature.getFragmentName().endsWith("PackageIcons")) {
                    // Firefox3, sometimes you disappoint me
                    test.addCmd("pause", "3000", "");
                }
                if (feature.getFragmentName().endsWith("ThemeExample")) {
                    // Theme samples needs to load an external application, give
                    // it some more time
                    test.addCmd("pause", "5000", "");
                }
                if (feature.getFragmentName().endsWith("ChameleonThemeExample")) {
                    // Safari 4 needs a really long time to load this demo app..
                    test.addCmd("pause", "5000", "");
                }

                test.addCmd("screenCapture", "", id);

            }
        }
    }

    private static boolean includeScreenshotInTest(String id) {
        if (id.startsWith("Date")) {
            return false;
        }
        if (id.equals("BrowserInformation")) {
            return false;
        }

        if (id.equals("WebEmbed")) {
            return false;
        }
        if (id.equals("JSApi")) {
            return false;
        }

        return true;
    }

    private static boolean needsPause(Feature feature) {
        if (feature.getFragmentName().endsWith("Embed")) {
            return true;
        }
        // the previous test (drag and drop files) may leave a notification open
        if (feature.getFragmentName().endsWith("LayoutMargin")) {
            return true;
        }
        if (feature.getFragmentName().endsWith("LoginForm")) {
            return true;
        }
        if (feature.getFragmentName().endsWith("PackageIcons")) {
            return true;
        }

        return false;
    }

    private static String getId(Feature feature) {
        // Sampler changed so that FragmentName is unique
        return feature.getFragmentName();
    }
}
