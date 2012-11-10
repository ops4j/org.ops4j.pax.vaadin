package com.vaadin.demo.sampler;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.demo.sampler.ActiveLink.LinkActivatedEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.URIHandler;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.PopupVisibilityEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedEvent;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class SamplerApplication extends Application {

    // All features in one container
    private static final HierarchicalContainer allFeatures = FeatureSet.FEATURES
            .getContainer(true);

    // init() inits
    private static final String[] THEMES = { "reindeer", "runo" };
    private static final String SAMPLER_THEME_NAME = "sampler";

    // used when trying to guess theme location
    private static String APP_URL = null;

    private String currentApplicationTheme = SAMPLER_THEME_NAME + "-"
            + THEMES[0];

    @Override
    public void init() {
        setMainWindow(new SamplerWindow());
        URL url = getURL();
        APP_URL = (url != null) ? getURL().toString() : "";
    }

    /**
     * Tries to guess theme location.
     * 
     * @return
     */
    public static String getThemeBase() {
        try {
            URI uri = new URI(APP_URL + "VAADIN/themes/" + SAMPLER_THEME_NAME
                    + "/");
            return uri.normalize().toString();
        } catch (Exception e) {
            System.err.println("Theme location could not be resolved:" + e);
        }
        return "/VAADIN/themes/" + SAMPLER_THEME_NAME + "/";
    }

    // Supports multiple browser windows
    @Override
    public Window getWindow(String name) {
        Window w = super.getWindow(name);
        if (w == null) {
            if (name.startsWith("src")) {
                w = new SourceWindow();
            } else {
                w = new SamplerWindow();
                w.setName(name);
            }

            addWindow(w);
        }
        return w;

    }

    /**
     * Gets absolute path for given Feature
     * 
     * @param f
     *            the Feature whose path to get, of null if not found
     * @return the path of the Feature
     */
    public static String getFullPathFor(Feature f) {
        if (f == null) {
            return "";
        }
        if (allFeatures.containsId(f)) {

            String path = f.getFragmentName();
            f = (Feature) allFeatures.getParent(f);
            while (f != null) {
                path = f.getFragmentName() + "/" + path;
                f = (Feature) allFeatures.getParent(f);
            }
            return path;

        }
        return "";
    }

    /**
     * Gets the instance for the given Feature class, e.g DummyFeature.class.
     * 
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Feature getFeatureFor(Class<?> clazz) {
        for (Iterator<Feature> it = (Iterator<Feature>) allFeatures
                .getItemIds().iterator(); it.hasNext();) {
            Feature f = it.next();
            if (f.getClass() == clazz) {
                return f;
            }
        }
        return null;
    }

    /**
     * The main window for Sampler, contains the full application UI.
     * 
     */
    class SamplerWindow extends Window {
        private final String TITLE = "Vaadin Sampler";

        private final ThemeResource EMPTY_THEME_ICON = new ThemeResource(
                "../sampler/sampler/icon-empty.png");

        private final ThemeResource SELECTED_THEME_ICON = new ThemeResource(
                "../sampler/sampler/select-bullet.png");

        private FeatureList currentList = new FeatureGrid();
        private final FeatureView featureView = new FeatureView();
        private final ObjectProperty<Feature> currentFeature = new ObjectProperty<Feature>(
                null, Feature.class);

        private final HorizontalSplitPanel mainSplit;
        private final Tree navigationTree;
        // itmill: UA-658457-6
        private final GoogleAnalytics webAnalytics = new GoogleAnalytics(
                "UA-658457-6", "none");
        // "backbutton"
        UriFragmentUtility uriFragmentUtility = new UriFragmentUtility();

        // breadcrumbs
        BreadCrumbs breadcrumbs = new BreadCrumbs();

        Button previousSample;
        Button nextSample;
        private ComboBox search;
        private ComboBox theme;

        @Override
        public void detach() {
            super.detach();

            search.setContainerDataSource(null);
            navigationTree.setContainerDataSource(null);
        }

        SamplerWindow() {
            // Main top/expanded-bottom layout
            VerticalLayout mainExpand = new VerticalLayout();
            setContent(mainExpand);
            setSizeFull();
            mainExpand.setSizeFull();
            setCaption(TITLE);
            setTheme(currentApplicationTheme);

            // topbar (navigation)
            HorizontalLayout nav = new HorizontalLayout();
            mainExpand.addComponent(nav);
            nav.setHeight("44px");
            nav.setWidth("100%");
            nav.setStyleName("topbar");
            nav.setSpacing(true);
            nav.setMargin(false, true, false, false);

            // Upper left logo
            Component logo = createLogo();
            nav.addComponent(logo);
            nav.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);

            // Breadcrumbs
            nav.addComponent(breadcrumbs);
            nav.setExpandRatio(breadcrumbs, 1);
            nav.setComponentAlignment(breadcrumbs, Alignment.MIDDLE_LEFT);

            // invisible analytics -component
            nav.addComponent(webAnalytics);

            // "backbutton"
            nav.addComponent(uriFragmentUtility);
            uriFragmentUtility.addListener(new FragmentChangedListener() {
                public void fragmentChanged(FragmentChangedEvent source) {
                    String frag = source.getUriFragmentUtility().getFragment();
                    if (frag != null && frag.contains("/")) {
                        String[] parts = frag.split("/");
                        frag = parts[parts.length - 1];
                    }
                    setFeature(frag);
                }
            });

            // Main left/right split; hidden menu tree
            mainSplit = new HorizontalSplitPanel();
            mainSplit.setSizeFull();
            mainSplit.setStyleName("main-split");
            mainExpand.addComponent(mainSplit);
            mainExpand.setExpandRatio(mainSplit, 1);

            // Select theme
            // Component themeSelect = createThemeSelect();
            // nav.addComponent(themeSelect);
            // nav.setComponentAlignment(themeSelect, Alignment.MIDDLE_LEFT);

            // Layouts for top area buttons
            HorizontalLayout quicknav = new HorizontalLayout();
            HorizontalLayout arrows = new HorizontalLayout();
            nav.addComponent(quicknav);
            nav.addComponent(arrows);
            nav.setComponentAlignment(quicknav, Alignment.MIDDLE_LEFT);
            nav.setComponentAlignment(arrows, Alignment.MIDDLE_LEFT);
            quicknav.setStyleName("segment");
            arrows.setStyleName("segment");

            // Previous sample
            previousSample = createPrevButton();
            arrows.addComponent(previousSample);
            // Next sample
            nextSample = createNextButton();
            arrows.addComponent(nextSample);
            // "Search" combobox
            Component searchComponent = createSearch();
            quicknav.addComponent(searchComponent);

            // Menu tree, initially shown
            CssLayout menuLayout = new CssLayout();
            ActiveLink allSamples = new ActiveLink("All Samples",
                    new ExternalResource("#"));
            menuLayout.addComponent(allSamples);
            navigationTree = createMenuTree();
            menuLayout.addComponent(navigationTree);
            mainSplit.setFirstComponent(menuLayout);

            // Show / hide tree
            Component treeSwitch = createTreeSwitch();
            quicknav.addComponent(treeSwitch);

            addListener(new CloseListener() {
                public void windowClose(CloseEvent e) {
                    if (getMainWindow() != SamplerWindow.this) {
                        SamplerApplication.this
                                .removeWindow(SamplerWindow.this);
                    }
                }
            });

            updateFeatureList(currentList);

        }

        public void removeSubwindows() {
            Collection<Window> wins = getChildWindows();
            if (null != wins) {
                for (Window w : wins) {
                    removeWindow(w);
                }
            }
        }

        /**
         * Displays a Feature(Set)
         * 
         * @param f
         *            the Feature(Set) to show
         */
        public void setFeature(Feature f) {
            if (f == FeatureSet.FEATURES) {
                // "All" is no longer in the tree, use null instead
                f = null;
            }
            currentFeature.setValue(f);
            String fragment = (f != null ? f.getFragmentName() : "");

            webAnalytics.trackPageview(fragment);
            uriFragmentUtility.setFragment(fragment, false);
            breadcrumbs.setPath(getFullPathFor(f));

            previousSample.setEnabled(f != null);
            nextSample.setEnabled(!allFeatures.isLastId(f));

            updateFeatureList(currentList);

            setCaption((f != null ? f.getName() + " }> " : "") + TITLE);
        }

        /**
         * Displays a Feature(Set) matching the given path, or the main view if
         * no matching Feature(Set) is found.
         * 
         * @param path
         *            the path of the Feature(Set) to show
         */
        public void setFeature(String path) {
            Feature f = FeatureSet.FEATURES.getFeature(path);
            setFeature(f);
        }

        /*
         * SamplerWindow helpers
         */

        private Component createSearch() {
            search = new ComboBox();
            search.setWidth("160px");
            search.setNewItemsAllowed(false);
            search.setFilteringMode(ComboBox.FILTERINGMODE_CONTAINS);
            search.setNullSelectionAllowed(true);
            search.setImmediate(true);
            search.setInputPrompt("Search samples...");
            search.setContainerDataSource(allFeatures);
            for (Iterator<?> it = allFeatures.getItemIds().iterator(); it
                    .hasNext();) {
                Object id = it.next();
                if (id instanceof FeatureSet) {
                    search.setItemIcon(id, new ClassResource("folder.gif",
                            SamplerApplication.this));
                }
            }
            search.addListener(new ComboBox.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {
                    Feature f = (Feature) event.getProperty().getValue();
                    if (f != null) {
                        SamplerWindow.this.setFeature(f);
                        event.getProperty().setValue(null);
                    }

                }
            });
            // TODO add icons for section/sample
            /*
             * PopupView pv = new PopupView("", search) { public void
             * changeVariables(Object source, Map variables) {
             * super.changeVariables(source, variables); if (isPopupVisible()) {
             * search.focus(); } } };
             */
            final PopupView pv = new PopupView("<span></span>", search);
            pv.addListener(new PopupView.PopupVisibilityListener() {
                public void popupVisibilityChange(PopupVisibilityEvent event) {
                    if (event.isPopupVisible()) {
                        search.focus();
                    }
                }
            });
            pv.setStyleName("quickjump");
            pv.setDescription("Quick jump");

            return pv;
        }

        private Component createThemeSelect() {
            theme = new ComboBox();
            theme.setWidth("32px");
            theme.setNewItemsAllowed(false);
            theme.setFilteringMode(ComboBox.FILTERINGMODE_CONTAINS);
            theme.setImmediate(true);
            theme.setNullSelectionAllowed(false);
            for (String themeName : THEMES) {
                String id = SAMPLER_THEME_NAME + "-" + themeName;
                theme.addItem(id);
                theme.setItemCaption(id, themeName.substring(0, 1)
                        .toUpperCase() + themeName.substring(1) + " theme");
                theme.setItemIcon(id, EMPTY_THEME_ICON);
            }

            final String currentWindowTheme = getTheme();
            theme.setValue(currentWindowTheme);
            theme.setItemIcon(currentWindowTheme, SELECTED_THEME_ICON);

            theme.addListener(new ComboBox.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {

                    final String newTheme = event.getProperty().getValue()
                            .toString();
                    setTheme(newTheme);

                    for (String themeName : THEMES) {
                        String id = SAMPLER_THEME_NAME + "-" + themeName;
                        theme.setItemIcon(id, EMPTY_THEME_ICON);
                    }

                    theme.setItemIcon(newTheme, SELECTED_THEME_ICON);
                    currentApplicationTheme = newTheme;
                }
            });

            theme.setStyleName("theme-select");
            theme.setDescription("Select Theme");

            return theme;
        }

        private Component createLogo() {
            Button logo = new NativeButton("", new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                    setFeature((Feature) null);
                }
            });
            logo.setDescription("↶ Home");
            logo.setStyleName(BaseTheme.BUTTON_LINK);
            logo.addStyleName("logo");
            return logo;
        }

        private Button createNextButton() {
            Button b = new NativeButton("", new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    Object curr = currentFeature.getValue();
                    Object next;
                    if (curr == null) {
                        // Navigate from main view to first sample.
                        next = allFeatures.firstItemId();
                    } else {
                        // Navigate to next sample
                        next = allFeatures.nextItemId(curr);
                    }
                    while (next != null && next instanceof FeatureSet) {
                        next = allFeatures.nextItemId(next);
                    }
                    if (next != null) {
                        currentFeature.setValue(next);
                    } else {
                        // could potentially occur if there is an empty section
                        showNotification("Last sample");
                    }
                }
            });
            b.setStyleName("next");
            b.setDescription("Jump to the next sample");
            return b;
        }

        private Button createPrevButton() {
            Button b = new NativeButton("", new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    Object curr = currentFeature.getValue();
                    Object prev = allFeatures.prevItemId(curr);
                    while (prev != null && prev instanceof FeatureSet) {
                        prev = allFeatures.prevItemId(prev);
                    }
                    currentFeature.setValue(prev);
                }
            });
            b.setEnabled(false);
            b.setStyleName("previous");
            b.setDescription("Jump to the previous sample");
            return b;
        }

        private Component createTreeSwitch() {
            final Button b = new NativeButton();
            b.setStyleName("tree-switch");
            b.addStyleName("down");
            b.setDescription("Toggle sample tree visibility");

            b.addListener(new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                    if (b.getStyleName().contains("down")) {
                        b.removeStyleName("down");
                        mainSplit.setSplitPosition(0);
                        navigationTree.setVisible(false);
                        mainSplit.setLocked(true);
                    } else {
                        b.addStyleName("down");
                        mainSplit.setSplitPosition(250, Sizeable.UNITS_PIXELS);
                        mainSplit.setLocked(false);
                        navigationTree.setVisible(true);
                    }
                }
            });
            mainSplit.setSplitPosition(250, Sizeable.UNITS_PIXELS);
            return b;
        }

        private Tree createMenuTree() {
            final Tree tree = new Tree();
            tree.setImmediate(true);
            tree.setStyleName("menu");
            tree.setContainerDataSource(allFeatures);
            currentFeature.addListener(new Property.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {
                    Feature f = (Feature) event.getProperty().getValue();
                    Feature v = (Feature) tree.getValue();
                    if ((f != null && !f.equals(v)) || (f == null && v != null)) {
                        tree.setValue(f);
                    }
                    removeSubwindows();
                }
            });
            for (int i = 0; i < FeatureSet.FEATURES.getFeatures().length; i++) {
                tree.expandItemsRecursively(FeatureSet.FEATURES.getFeatures()[i]);
            }
            tree.expandItemsRecursively(FeatureSet.FEATURES);
            tree.addListener(new Tree.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {
                    Feature f = (Feature) event.getProperty().getValue();
                    setFeature(f);
                }
            });
            tree.setItemStyleGenerator(new Tree.ItemStyleGenerator() {
                public String getStyle(Object itemId) {
                    Feature f = (Feature) itemId;
                    if (f.getSinceVersion().isNew()) {
                        return "new";
                    }
                    return null;
                }
            });
            return tree;
        }

        private void updateFeatureList(FeatureList list) {
            currentList = list;
            Feature val = currentFeature.getValue();
            if (val == null) {
                currentList.setFeatureContainer(allFeatures);
                mainSplit.setSecondComponent(currentList);
            } else if (val instanceof FeatureSet) {
                currentList.setFeatureContainer(((FeatureSet) val)
                        .getContainer(true));
                mainSplit.setSecondComponent(currentList);
            } else {
                mainSplit.setSecondComponent(featureView);
                featureView.setFeature(val);
            }

        }

    }

    private class BreadCrumbs extends CustomComponent implements
            ActiveLink.LinkActivatedListener {
        HorizontalLayout layout;

        BreadCrumbs() {
            layout = new HorizontalLayout();
            layout.setSpacing(true);
            setCompositionRoot(layout);
            setStyleName("breadcrumbs");
            setPath(null);
        }

        public void setPath(String path) {
            // could be optimized: always builds path from scratch
            layout.removeAllComponents();

            { // home
                ActiveLink link = new ActiveLink("Home", new ExternalResource(
                        "#"));
                link.addListener(this);
                layout.addComponent(link);
            }

            if (path != null && !"".equals(path)) {
                String parts[] = path.split("/");
                ActiveLink link = null;
                for (int i = 0; i < parts.length; i++) {
                    Label separator = new Label("&raquo;", Label.CONTENT_XHTML);
                    separator.setSizeUndefined();
                    layout.addComponent(separator);
                    Feature f = FeatureSet.FEATURES.getFeature(parts[i]);
                    link = new ActiveLink(f.getName(), new ExternalResource("#"
                            + f.getFragmentName()));
                    link.setData(f);
                    link.addListener(this);
                    layout.addComponent(link);
                }
                if (link != null) {
                    link.setStyleName("bold");
                }
            }

        }

        public void linkActivated(LinkActivatedEvent event) {
            if (!event.isLinkOpened()) {
                ((SamplerWindow) getWindow()).setFeature((Feature) event
                        .getActiveLink().getData());
            }
        }
    }

    /**
     * Components capable of listing Features should implement this.
     * 
     */
    interface FeatureList extends Component {
        /**
         * Shows the given Features
         * 
         * @param c
         *            Container with Features to show.
         */
        public void setFeatureContainer(HierarchicalContainer c);
    }

    /**
     * Table -mode FeatureList. Displays the features in a Table.
     */
    @SuppressWarnings("unused")
    private class FeatureTable extends Table implements FeatureList {
        FeatureTable() {
            setStyleName("featuretable");
            alwaysRecalculateColumnWidths = true;
            setSelectable(false);
            setSizeFull();
            setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
            addGeneratedColumn(Feature.PROPERTY_ICON,
                    new Table.ColumnGenerator() {
                        public Component generateCell(Table source,
                                Object itemId, Object columnId) {
                            Feature f = (Feature) itemId;
                            if (f instanceof FeatureSet) {
                                // no icon for sections
                                return null;
                            }
                            String resId = "75-" + f.getIconName();
                            Resource res = getSampleIcon(resId);
                            Embedded emb = new Embedded("", res);
                            emb.setWidth("48px");
                            emb.setHeight("48px");
                            emb.setType(Embedded.TYPE_IMAGE);
                            return emb;
                        }

                    });
            addGeneratedColumn("", new Table.ColumnGenerator() {
                public Component generateCell(Table source, Object itemId,
                        Object columnId) {
                    final Feature feature = (Feature) itemId;
                    if (feature instanceof FeatureSet) {
                        return null;
                    } else {
                        ActiveLink b = new ActiveLink("View sample ‣",
                                new ExternalResource("#"
                                        + feature.getFragmentName()));
                        b.addListener(new ActiveLink.LinkActivatedListener() {
                            public void linkActivated(LinkActivatedEvent event) {
                                if (!event.isLinkOpened()) {
                                    ((SamplerWindow) getWindow())
                                            .setFeature(feature);
                                }
                            }
                        });

                        b.setStyleName(BaseTheme.BUTTON_LINK);
                        return b;
                    }
                }

            });

            addListener(new ItemClickListener() {
                public void itemClick(ItemClickEvent event) {
                    Feature f = (Feature) event.getItemId();
                    if (event.getButton() == ItemClickEvent.BUTTON_MIDDLE
                            || event.isCtrlKey() || event.isShiftKey()) {
                        getWindow().open(
                                new ExternalResource(getURL() + "#"
                                        + f.getFragmentName()), "_blank");
                    } else {
                        ((SamplerWindow) getWindow()).setFeature(f);
                    }
                }
            });

            setCellStyleGenerator(new CellStyleGenerator() {
                public String getStyle(Object itemId, Object propertyId) {
                    if (propertyId == null && itemId instanceof FeatureSet) {
                        if (allFeatures.isRoot(itemId)) {
                            return "section";
                        } else {
                            return "subsection";
                        }

                    }
                    return null;
                }
            });
        }

        public void setFeatureContainer(HierarchicalContainer c) {
            setContainerDataSource(c);
            setVisibleColumns(new Object[] { Feature.PROPERTY_ICON,
                    Feature.PROPERTY_NAME, "" });
            setColumnWidth(Feature.PROPERTY_ICON, 60);

        }

    }

    private final HashMap<Object, Resource> sampleIconCache = new HashMap<Object, Resource>();

    private Resource getSampleIcon(String resId) {
        Resource res = sampleIconCache.get(resId);
        if (res == null) {
            res = new ThemeResource("../sampler/icons/sampleicons/" + resId);
            sampleIconCache.put(resId, res);
        }
        return res;
    }

    private class FeatureGrid extends Panel implements FeatureList {

        private final CssLayout grid = new CssLayout();

        FeatureGrid() {
            setSizeFull();
            setScrollable(true);
            setContent(grid);
            setStyleName(Reindeer.PANEL_LIGHT);
            grid.setStyleName("grid");
        }

        @SuppressWarnings("unchecked")
        public void setFeatureContainer(HierarchicalContainer c) {
            grid.removeAllComponents();
            Collection<Feature> features = (Collection<Feature>) c.getItemIds();

            CssLayout rootSet = new CssLayout();
            Label rootTitle = null;

            CssLayout highlightRow = new CssLayout();
            highlightRow.setStyleName("highlight-row");

            int sampleCount = 0;
            for (Iterator<Feature> it = features.iterator(); it.hasNext();) {
                final Feature f = it.next();
                if (f instanceof FeatureSet) {
                    if (c.isRoot(f)) {
                        if (rootTitle != null) {
                            rootTitle.setValue("<em>" + sampleCount
                                    + " samples</em>" + rootTitle.getValue());
                            sampleCount = 0;
                        }
                        rootTitle = new Label("<h2>"
                                + f.getName()
                                + "</h2><span>"
                                + f.getDescription().substring(0,
                                        f.getDescription().indexOf(".") + 1)
                                + "</span>", Label.CONTENT_XHTML);
                        rootTitle.setSizeUndefined();
                        if (f.getRelatedFeatures() != null) {
                            rootTitle.setValue("<em>"
                                    + f.getRelatedFeatures().length
                                    + " samples</em>" + rootTitle.getValue());
                        }
                        rootSet = new CssLayout();
                        rootSet.setStyleName("root");
                        rootTitle.setStyleName("root-section");
                        grid.addComponent(rootTitle);
                        grid.addComponent(rootSet);
                    }

                } else {
                    sampleCount++;
                    String resId = "75-" + f.getIconName();
                    Resource res = getSampleIcon(resId);
                    if (rootSet.getParent() == null) {
                        // This sample is directly inside a non root feature
                        // set, we present these with higher priority

                        if (rootTitle == null) {
                            Feature parent = (Feature) allFeatures.getParent(f);
                            rootTitle = new Label("<h2>" + parent.getName()
                                    + "</h2>", Label.CONTENT_XHTML);
                            rootTitle
                                    .setStyleName("root-section highlights-title");
                            rootTitle.setSizeUndefined();
                            grid.addComponent(rootTitle);

                            if (parent.getDescription() != null) {
                                Label desc = new Label(parent.getDescription(),
                                        Label.CONTENT_XHTML);
                                desc.setStyleName("highlights-description");
                                desc.setSizeUndefined();
                                grid.addComponent(desc);
                            }
                        }

                        // Two samples per row
                        if (sampleCount % 2 == 1) {
                            highlightRow = new CssLayout();
                            highlightRow.setStyleName("highlight-row");
                            grid.addComponent(highlightRow);
                        }

                        CssLayout l = new CssLayout();
                        l.setStyleName("highlight");

                        ActiveLink sample = new ActiveLink(f.getName(),
                                new ExternalResource("#" + f.getFragmentName()));
                        sample.setIcon(res);
                        if (f.getSinceVersion().isNew()) {
                            sample.addStyleName("new");
                        }
                        l.addComponent(sample);

                        if (f.getDescription() != null
                                && f.getDescription() != "") {
                            Label desc = new Label(
                                    f.getDescription()
                                            .substring(
                                                    0,
                                                    f.getDescription().indexOf(
                                                            ".") + 1),
                                    Label.CONTENT_XHTML);
                            desc.setSizeUndefined();
                            l.addComponent(desc);
                        }

                        highlightRow.addComponent(l);

                    } else {
                        ActiveLink sample = new ActiveLink(f.getName(),
                                new ExternalResource("#" + f.getFragmentName()));
                        sample.setStyleName(BaseTheme.BUTTON_LINK);
                        sample.addStyleName("screenshot");
                        if (f.getDescription() != null
                                && f.getDescription() != "") {
                            sample.setDescription(f.getDescription().substring(
                                    0, f.getDescription().indexOf(".") + 1));
                        }
                        if (f.getSinceVersion().isNew()) {
                            sample.addStyleName("new");
                        }
                        sample.setIcon(res);
                        rootSet.addComponent(sample);
                    }
                }
            }
            if (rootTitle != null) {
                rootTitle.setValue("<em>" + sampleCount + " samples</em>"
                        + rootTitle.getValue());
            }
        }
    }

    public static HierarchicalContainer getAllFeatures() {
        return allFeatures;
    }

    public class SourceWindow extends Window {
        public SourceWindow() {
            addURIHandler(new URIHandler() {
                public DownloadStream handleURI(URL context, String relativeUri) {
                    Feature f = FeatureSet.FEATURES.getFeature(relativeUri);
                    if (f != null) {
                        addComponent(new CodeLabel(f.getSource()));
                    } else if ("ExampleUtil".equals(relativeUri)) {
                        String source = "Sorry, could not find source for ExampleUtil";
                        try {
                            source = SourceReader
                                    .getSourceForClass(ExampleUtil.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        addComponent(new CodeLabel(source));
                    } else {
                        addComponent(new Label("Sorry, no source found for "
                                + relativeUri));
                    }
                    return null;
                }

            });

            addListener(new CloseListener() {
                public void windowClose(CloseEvent e) {
                    SamplerApplication.this.removeWindow(SourceWindow.this);
                }
            });
        }
    }

    @Override
    public void close() {
        removeWindow(getMainWindow());

        super.close();
    }

    public static String getBaseUrl() {
        return APP_URL;
    }
}
