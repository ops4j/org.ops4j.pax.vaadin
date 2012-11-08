package com.vaadin.demo.sampler;

import java.util.HashMap;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.demo.sampler.features.accordions.AccordionDisabled;
import com.vaadin.demo.sampler.features.accordions.AccordionIcons;
import com.vaadin.demo.sampler.features.blueprints.ProminentPrimaryAction;
import com.vaadin.demo.sampler.features.buttons.ButtonDisableOnClick;
import com.vaadin.demo.sampler.features.buttons.ButtonLink;
import com.vaadin.demo.sampler.features.buttons.ButtonPush;
import com.vaadin.demo.sampler.features.buttons.CheckBoxes;
import com.vaadin.demo.sampler.features.commons.BrowserInformation;
import com.vaadin.demo.sampler.features.commons.Errors;
import com.vaadin.demo.sampler.features.commons.Icons;
import com.vaadin.demo.sampler.features.commons.JSApi;
import com.vaadin.demo.sampler.features.commons.PackageIcons;
import com.vaadin.demo.sampler.features.commons.Tooltips;
import com.vaadin.demo.sampler.features.dates.DateInline;
import com.vaadin.demo.sampler.features.dates.DateLocale;
import com.vaadin.demo.sampler.features.dates.DatePopup;
import com.vaadin.demo.sampler.features.dates.DatePopupInputPrompt;
import com.vaadin.demo.sampler.features.dates.DatePopupKeyboardNavigation;
import com.vaadin.demo.sampler.features.dates.DateResolution;
import com.vaadin.demo.sampler.features.dates.DateTimeZone;
import com.vaadin.demo.sampler.features.dragndrop.DragDropHtml5FromDesktop;
import com.vaadin.demo.sampler.features.dragndrop.DragDropRearrangeComponents;
import com.vaadin.demo.sampler.features.dragndrop.DragDropServerValidation;
import com.vaadin.demo.sampler.features.dragndrop.DragDropTableTree;
import com.vaadin.demo.sampler.features.dragndrop.DragDropTreeSorting;
import com.vaadin.demo.sampler.features.embedded.FlashEmbed;
import com.vaadin.demo.sampler.features.embedded.ImageEmbed;
import com.vaadin.demo.sampler.features.embedded.WebEmbed;
import com.vaadin.demo.sampler.features.form.FormAdvancedLayout;
import com.vaadin.demo.sampler.features.form.FormBasic;
import com.vaadin.demo.sampler.features.form.LoginForm;
import com.vaadin.demo.sampler.features.layouts.AbsoluteLayoutBasic;
import com.vaadin.demo.sampler.features.layouts.ApplicationLayout;
import com.vaadin.demo.sampler.features.layouts.ClickableLayoutBasic;
import com.vaadin.demo.sampler.features.layouts.CssLayouts;
import com.vaadin.demo.sampler.features.layouts.CustomLayouts;
import com.vaadin.demo.sampler.features.layouts.ExpandingComponent;
import com.vaadin.demo.sampler.features.layouts.GridLayoutBasic;
import com.vaadin.demo.sampler.features.layouts.HorizontalLayoutBasic;
import com.vaadin.demo.sampler.features.layouts.LayoutAlignment;
import com.vaadin.demo.sampler.features.layouts.LayoutMargin;
import com.vaadin.demo.sampler.features.layouts.LayoutSpacing;
import com.vaadin.demo.sampler.features.layouts.SplitPanelBasic;
import com.vaadin.demo.sampler.features.layouts.SplitPanelPositioning;
import com.vaadin.demo.sampler.features.layouts.VerticalLayoutBasic;
import com.vaadin.demo.sampler.features.layouts.WebLayout;
import com.vaadin.demo.sampler.features.link.LinkCurrentWindow;
import com.vaadin.demo.sampler.features.link.LinkNoDecorations;
import com.vaadin.demo.sampler.features.link.LinkSizedWindow;
import com.vaadin.demo.sampler.features.menubar.BasicMenuBar;
import com.vaadin.demo.sampler.features.menubar.MenuBarCheckableItems;
import com.vaadin.demo.sampler.features.menubar.MenuBarCollapsing;
import com.vaadin.demo.sampler.features.menubar.MenuBarHiddenItems;
import com.vaadin.demo.sampler.features.menubar.MenuBarHtmlItems;
import com.vaadin.demo.sampler.features.menubar.MenuBarItemStyles;
import com.vaadin.demo.sampler.features.menubar.MenuBarKeyboardNavigation;
import com.vaadin.demo.sampler.features.menubar.MenuBarTooltips;
import com.vaadin.demo.sampler.features.menubar.MenuBarWithIcons;
import com.vaadin.demo.sampler.features.notifications.NotificationCustom;
import com.vaadin.demo.sampler.features.notifications.NotificationError;
import com.vaadin.demo.sampler.features.notifications.NotificationHtml;
import com.vaadin.demo.sampler.features.notifications.NotificationHumanized;
import com.vaadin.demo.sampler.features.notifications.NotificationTray;
import com.vaadin.demo.sampler.features.notifications.NotificationWarning;
import com.vaadin.demo.sampler.features.panels.PanelBasic;
import com.vaadin.demo.sampler.features.panels.PanelLight;
import com.vaadin.demo.sampler.features.popupviews.PopupViewClosing;
import com.vaadin.demo.sampler.features.popupviews.PopupViewContents;
import com.vaadin.demo.sampler.features.progressindicator.ProgressIndicators;
import com.vaadin.demo.sampler.features.selects.ComboBoxContains;
import com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt;
import com.vaadin.demo.sampler.features.selects.ComboBoxNewItems;
import com.vaadin.demo.sampler.features.selects.ComboBoxPlain;
import com.vaadin.demo.sampler.features.selects.ComboBoxStartsWith;
import com.vaadin.demo.sampler.features.selects.ListSelectMultiple;
import com.vaadin.demo.sampler.features.selects.ListSelectSingle;
import com.vaadin.demo.sampler.features.selects.NativeSelection;
import com.vaadin.demo.sampler.features.selects.OptionGroupDisabledItems;
import com.vaadin.demo.sampler.features.selects.OptionGroupHtmlItems;
import com.vaadin.demo.sampler.features.selects.OptionGroups;
import com.vaadin.demo.sampler.features.selects.TwinColumnSelect;
import com.vaadin.demo.sampler.features.shortcuts.ShortcutBasics;
import com.vaadin.demo.sampler.features.shortcuts.ShortcutScope;
import com.vaadin.demo.sampler.features.slider.SliderHorizontal;
import com.vaadin.demo.sampler.features.slider.SliderKeyboardNavigation;
import com.vaadin.demo.sampler.features.slider.SliderVertical;
import com.vaadin.demo.sampler.features.table.TableActions;
import com.vaadin.demo.sampler.features.table.TableCellStyling;
import com.vaadin.demo.sampler.features.table.TableClickListeners;
import com.vaadin.demo.sampler.features.table.TableColumnAlignment;
import com.vaadin.demo.sampler.features.table.TableColumnCollapsing;
import com.vaadin.demo.sampler.features.table.TableColumnHeaders;
import com.vaadin.demo.sampler.features.table.TableColumnReordering;
import com.vaadin.demo.sampler.features.table.TableFooter;
import com.vaadin.demo.sampler.features.table.TableHeaderIcons;
import com.vaadin.demo.sampler.features.table.TableKeyboardNavigation;
import com.vaadin.demo.sampler.features.table.TableLazyLoading;
import com.vaadin.demo.sampler.features.table.TableMouseEvents;
import com.vaadin.demo.sampler.features.table.TableMultipleSelection;
import com.vaadin.demo.sampler.features.table.TableRowHeaders;
import com.vaadin.demo.sampler.features.table.TableRowStyling;
import com.vaadin.demo.sampler.features.table.TableSorting;
import com.vaadin.demo.sampler.features.tabsheets.TabSheetClosing;
import com.vaadin.demo.sampler.features.tabsheets.TabSheetDisabled;
import com.vaadin.demo.sampler.features.tabsheets.TabSheetIcons;
import com.vaadin.demo.sampler.features.tabsheets.TabSheetScrolling;
import com.vaadin.demo.sampler.features.tabsheets.TabSheetStyleName;
import com.vaadin.demo.sampler.features.text.LabelPlain;
import com.vaadin.demo.sampler.features.text.LabelPreformatted;
import com.vaadin.demo.sampler.features.text.LabelRich;
import com.vaadin.demo.sampler.features.text.RichTextEditor;
import com.vaadin.demo.sampler.features.text.TextArea;
import com.vaadin.demo.sampler.features.text.TextFieldInputPrompt;
import com.vaadin.demo.sampler.features.text.TextFieldSecret;
import com.vaadin.demo.sampler.features.text.TextFieldSingle;
import com.vaadin.demo.sampler.features.text.TextFieldTextChangeEvent;
import com.vaadin.demo.sampler.features.trees.TreeActions;
import com.vaadin.demo.sampler.features.trees.TreeKeyboardNavigation;
import com.vaadin.demo.sampler.features.trees.TreeMouseEvents;
import com.vaadin.demo.sampler.features.trees.TreeMultiSelect;
import com.vaadin.demo.sampler.features.trees.TreeSingleSelect;
import com.vaadin.demo.sampler.features.treetable.TreeTableBasic;
import com.vaadin.demo.sampler.features.treetable.TreeTableCellStyles;
import com.vaadin.demo.sampler.features.treetable.TreeTableContextMenu;
import com.vaadin.demo.sampler.features.treetable.TreeTableSelection;
import com.vaadin.demo.sampler.features.treetable.TreeTableTooltips;
import com.vaadin.demo.sampler.features.upload.ImmediateUpload;
import com.vaadin.demo.sampler.features.upload.UploadBasic;
import com.vaadin.demo.sampler.features.upload.UploadWithProgressMonitoring;
import com.vaadin.demo.sampler.features.windows.NativeWindow;
import com.vaadin.demo.sampler.features.windows.Subwindow;
import com.vaadin.demo.sampler.features.windows.SubwindowAutoSized;
import com.vaadin.demo.sampler.features.windows.SubwindowClose;
import com.vaadin.demo.sampler.features.windows.SubwindowModal;
import com.vaadin.demo.sampler.features.windows.SubwindowPositioned;
import com.vaadin.demo.sampler.features.windows.SubwindowSized;
import com.vaadin.demo.sampler.themes.ChameleonThemeExample;
import com.vaadin.demo.sampler.themes.ReindeerThemeExample;
import com.vaadin.demo.sampler.themes.RunoThemeExample;

/**
 * Contains the FeatureSet implementation and the structure for the feature
 * 'tree'.
 * <p>
 * Each set is implemented as it's own class to facilitate linking to sets in
 * the same way as linking to individual features.
 * </p>
 * 
 */
@SuppressWarnings("serial")
public class FeatureSet extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    /*
     * MAIN structure; root is always a FeatureSet that is not shown
     */
    public static final FeatureSet FEATURES = new FeatureSet("", new Feature[] {
            // Main sets
            // new Blueprints(), disabled for now, until more samples
            // are ready
            new Basics(), //
            new ValueInput(), //
            new FormsAndData(), //
            new GridsAndTrees(), //
            new DragDrop(), //
            new Layouting(), //
            new Windowing(), //
            new Themes()
    });

    public static class Blueprints extends FeatureSet {
        public Blueprints() {
            super("Blueprints", new Feature[] {
            // Blueprints
            new ProminentPrimaryAction(), //
            });
        }
    }

    public static class Basics extends FeatureSet {
        public Basics() {
            super("Basics", "UI Basics",
                    "The building blocks of any web application interface",
                    new Feature[] {
                            //
                            new Tooltips(), //
                            new Icons(), //
                            new PackageIcons(), //
                            new Errors(), //
                            new ProgressIndicators(), //
                            new JSApi(), //
                            new BrowserInformation(), //
                            new Buttons(), //
                            new Links(), //
                            new Texts(), //
                            new Embedding(), //
                            new Shortcuts(), //

                    });
        }
    }

    public static class Shortcuts extends FeatureSet {
        public Shortcuts() {
            super("Shortcuts", "Keyboard shortcuts",
                    "Binding keyboard shortcuts to actions", //
                    new Feature[] {
                            //
                            new ShortcutBasics(), //
                            new ShortcutScope(), //
                    });
        }

        @Override
        public Version getSinceVersion() {
            return Version.V63;
        }
    }

    public static class ValueInput extends FeatureSet {
        public ValueInput() {
            super("Input", "Value Input Components",
                    "Components used for gathering input from the user",
                    new Feature[] {
                            //
                            new Dates(), //
                            new TextFields(), //
                            new Selects(), //
                            new Sliders(), //
                            new Uploads(), //
                    });
        }
    }

    public static class FormsAndData extends FeatureSet {
        public FormsAndData() {
            super("FormsAndData", "Forms and Data Model",
                    "Grouping fields and data manipulation samples",
                    new Feature[] {
                    //
                    // new Validation(), // TODO needed?
                    new Forms(), // TODO more samples
                    // new Buffering(), // TODO
                    });
        }
    }

    public static class GridsAndTrees extends FeatureSet {
        public GridsAndTrees() {
            super(
                    "GridsAndTrees",
                    "Grids and Trees",
                    "For large sets of data, the Table (Grid) and Tree components come in handy",
                    new Feature[] {
                            //
                            new Tables(), //
                            new Trees(), //
                            new TreeTables(),
                    });
        }
    }

    public static class Layouting extends FeatureSet {
        public Layouting() {
            super("ComponentContainers", "Layouts & Component Containers",
                    "Laying out and grouping components together",
                    new Feature[] {
                            //
                            new Layouts(), //
                            new Panels(), //
                            new Tabsheets(), //
                            new Accordions(), //
                    });
        }
    }

    public static class Windowing extends FeatureSet {
        public Windowing() {
            super("Windowing", "Windows, Popups and Navigation", "",
                    new Feature[] {
                            //
                            new MenuBars(), //
                            new Windows(), //
                            new PopupViews(), //
                            new Notifications(), //
                    // new URIFragment(), // TODO
                    });
        }
    }

    public static class Buttons extends FeatureSet {
        public Buttons() {
            super(
                    "Buttons",
                    "Buttons",
                    "A button is one of the fundamental building blocks of any application.",
                    new Feature[] {
                            //
                            new ButtonPush(), // basic
                            new ButtonDisableOnClick(), // disable on click
                            new ButtonLink(), // link
                            new CheckBoxes(), // switch/checkbox

                    });
        }
    }

    public static class Links extends FeatureSet {
        public Links() {
            super(
                    "Links",
                    "Links",
                    "An external link. This is the basic HTML-style link, changing the url of the browser w/o triggering a server-side event (like the link-styled Button).",
                    new Feature[] {
                            //
                            new LinkCurrentWindow(), // basic
                            new LinkNoDecorations(), // new win
                            new LinkSizedWindow(), // new win

                    });
        }
    }

    public static class MenuBars extends FeatureSet {
        public MenuBars() {
            super(
                    "Menubars",
                    "Menubars",
                    "MenuBar has hierarchical set of actions that are presented in drop down menus. The root level is a horizontal list of items that open the drop down menus.",
                    new Feature[] {
                            //
                            new BasicMenuBar(), // basic
                            new MenuBarWithIcons(), // with icons
                            new MenuBarCheckableItems(), // selectable items
                            new MenuBarCollapsing(), // collapsing items
                            new MenuBarHiddenItems(), // collapsing items
                            new MenuBarItemStyles(), // item styles
                            new MenuBarKeyboardNavigation(), // keyboard
                            new MenuBarTooltips(), // tooltips
                            new MenuBarHtmlItems(), // HTML items

                    });
        }
    }

    public static class Notifications extends FeatureSet {
        public Notifications() {
            super(
                    "Notifications",
                    "Notifications",
                    "Notifications are lightweight informational messages, used to inform the user of various events.",
                    new Feature[] {
                            //
                            new NotificationHumanized(), // humanized
                            new NotificationWarning(), // warning
                            new NotificationTray(), // tray
                            new NotificationError(), // error
                            new NotificationCustom(), // error
                            new NotificationHtml(), // plain text notifications
                    });
        }
    }

    public static class Selects extends FeatureSet {
        public Selects() {
            super("Selects", new Feature[] {
                    //
                    new ListSelectSingle(), //
                    new ListSelectMultiple(), //
                    new TwinColumnSelect(), //
                    new OptionGroups(), //
                    new OptionGroupDisabledItems(), //
                    new OptionGroupHtmlItems(), //
                    new NativeSelection(), //
                    new ComboBoxPlain(), //
                    new ComboBoxInputPrompt(), //
                    new ComboBoxStartsWith(), //
                    new ComboBoxContains(), //
                    new ComboBoxNewItems(), //

            });
        }
    }

    public static class Sliders extends FeatureSet {
        public Sliders() {
            super(
                    "Sliders",
                    "Sliders",
                    "Slider component allows the user to select a numeric value from a specified range of values.",
                    new Feature[] {
                            //
                            new SliderHorizontal(), //
                            new SliderVertical(), //
                            new SliderKeyboardNavigation(), //
                    });
        }
    }

    public static class Layouts extends FeatureSet {
        public Layouts() {
            super(
                    "Layouts",
                    "Layouts",
                    "Making a usable, good looking, dynamic layout can be tricky, but with the right tools almost anything is possible.<br/>",
                    new Feature[] {
                            //
                            new LayoutMargin(), //
                            new LayoutSpacing(), //
                            new VerticalLayoutBasic(), //
                            new HorizontalLayoutBasic(), //
                            new GridLayoutBasic(), //
                            new AbsoluteLayoutBasic(), //
                            new LayoutAlignment(), //
                            new ExpandingComponent(), //
                            new SplitPanelBasic(), //
                            new SplitPanelPositioning(), //
                            new ApplicationLayout(), //
                            new WebLayout(), //
                            new CustomLayouts(), //
                            new CssLayouts(),//
                            new ClickableLayoutBasic(),//
                    });
        }
    }

    public static class Tabsheets extends FeatureSet {
        public Tabsheets() {
            super(
                    "Tabsheets",
                    "Tabsheets",
                    "A Tabsheet organizes multiple components so that only the one component associated with the currently selected 'tab' is shown. Typically a tab will contain a Layout, which in turn may contain many components.",
                    new Feature[] {
                            //
                            new TabSheetIcons(), //
                            new TabSheetScrolling(), //
                            new TabSheetDisabled(), //
                            new TabSheetClosing(), //
                            new TabSheetStyleName(), //
                    });
        }
    }

    public static class Accordions extends FeatureSet {
        public Accordions() {
            super(
                    "Accordions",
                    "Accordions",
                    "An accordion component is a specialized case of a tabsheet."
                            + " Within an accordion, the tabs are organized vertically,"
                            + " and the content will be shown directly below the tab.",
                    new Feature[] {
                            //
                            new AccordionIcons(), //
                            new AccordionDisabled(), //
                    });
        }
    }

    public static class Panels extends FeatureSet {
        public Panels() {
            super(
                    "Panels",
                    "Panels",
                    "Panel is a simple container that supports scrolling.<br/>It's internal layout (by default VerticalLayout) can be configured or exchanged to get desired results. Components that are added to the Panel will in effect be added to the layout.",
                    new Feature[] {
                            //
                            new PanelBasic(), //
                            new PanelLight(), //
                    });
        }
    }

    public static class PopupViews extends FeatureSet {
        public PopupViews() {
            super(
                    "PopupViews",
                    "PopupViews",
                    "PopupView is a container that allows to disclose parts of the interface to a popup dialog. It only presents a minimal amount of information initially.",
                    new Feature[] {
                            //
                            new PopupViewClosing(), //
                            new PopupViewContents(), //
                    });
        }
    }

    public static class Forms extends FeatureSet {
        public Forms() {
            super("Forms", "Forms",
                    "The Form -component provides a convenient way to organize"
                            + " related fields visually.", new Feature[] {
                            //
                            new FormBasic(), //
                            new FormAdvancedLayout(), //
                            new LoginForm(), //
                    });
        }
    }

    public static class Uploads extends FeatureSet {
        public Uploads() {
            super(
                    "Uploads",
                    "Uploads",
                    "Upload is a component providing a method for clients to send files to server.",
                    new Feature[] {
                            //
                            new UploadBasic(), //
                            new ImmediateUpload(), //
                            new UploadWithProgressMonitoring(), //
                    });
        }
    }

    public static class Windows extends FeatureSet {
        public Windows() {
            super(
                    "Windows",
                    "Windows",
                    "Windows are one essential part of desktop style applications. Windows can (for instance) organize the UI, save space (popup windows), focus attention (modal windows), or provide multiple views for the same data for multitasking or dual screen setups (native browser windows).",
                    new Feature[] {
                            //
                            new Subwindow(), //
                            new SubwindowModal(), //
                            new SubwindowAutoSized(), //
                            new SubwindowSized(), //
                            new SubwindowPositioned(), //
                            new SubwindowClose(), //
                            new NativeWindow(), //
                    });
        }
    }

    public static class Tables extends FeatureSet {
        public Tables() {
            super(
                    "Table (Grid)",
                    "Table (Grid)",
                    "A Table, also known as a (Data)Grid, can be used to show data in a tabular fashion. It's well suited for showing large datasets.",
                    new Feature[] {
                            //
                            new TableHeaderIcons(), //
                            new TableColumnHeaders(), //
                            new TableFooter(), //
                            new TableColumnReordering(), //
                            new TableColumnCollapsing(), //
                            new TableColumnAlignment(), //
                            new TableCellStyling(), //
                            new TableSorting(), //
                            new TableRowHeaders(), //
                            new TableRowStyling(), //
                            new TableActions(), //
                            new TableMouseEvents(), //
                            new TableLazyLoading(), //
                            new TableMultipleSelection(), //
                            new TableClickListeners(), //
                            new TableKeyboardNavigation(), //
                    });
        }
    }
    
    public static class TreeTables extends FeatureSet {
        public TreeTables() {
            super(
                    "TreeTable",
                    "TreeTable",
                    "A TreeTable can be used to display hierarchical information in a tabular fashion.",
                    new Feature[]{
                            new TreeTableBasic(),
                            new TreeTableCellStyles(),
                            new TreeTableContextMenu(),
                            new TreeTableSelection(),
                            new TreeTableTooltips()
                    });
        }
    }

    public static class Texts extends FeatureSet {
        public Texts() {
            super(
                    "Texts",
                    "Texts",
                    "A label is a simple component that allows you to add (optionally formatted) text components to your application",
                    new Feature[] {
                            //
                            new LabelPlain(), //
                            new LabelPreformatted(), //
                            new LabelRich(), //
                    });
        }
    }

    public static class Embedding extends FeatureSet {
        public Embedding() {
            super("Embedding", "Embedding",
                    "How to add non-textual content to your applications",
                    new Feature[] {
                            //
                            new ImageEmbed(), //
                            new FlashEmbed(), //
                            new WebEmbed(), //
                    });
        }
    }

    public static class TextFields extends FeatureSet {
        public TextFields() {
            super(
                    "TextFields",
                    "Text inputs",
                    "Text inputs are probably the most needed components in any application that require user input or editing.",
                    new Feature[] {
                            //
                            new TextFieldSingle(), //
                            new TextFieldSecret(), //
                            new TextFieldInputPrompt(), //
                            new TextFieldTextChangeEvent(), //
                            new TextArea(), //
                            new RichTextEditor(), //
                    });
        }
    }

    public static class Trees extends FeatureSet {
        public Trees() {
            super(
                    "Trees",
                    "Trees",
                    "The Tree component provides a natural way to represent data that has hierarchical relationships, such as filesystems or message threads.",
                    new Feature[] {
                            //
                            new TreeSingleSelect(), //
                            new TreeMultiSelect(), //
                            new TreeActions(), //
                            new TreeMouseEvents(), //
                            new TreeKeyboardNavigation(), //
                    });
        }
    }

    public static class Dates extends FeatureSet {
        public Dates() {
            super(
                    "Dates",
                    "Dates",
                    "The DateField component can be used to produce various date and time input fields with different resolutions. The date and time format used with this component is reported to Vaadin by the browser.",
                    new Feature[] {
                            //
                            new DatePopup(), //
                            new DatePopupInputPrompt(), //
                            new DatePopupKeyboardNavigation(), //
                            new DateInline(), //
                            new DateLocale(), //
                            new DateTimeZone(), //
                            new DateResolution(), //
                    });
        }
    }

    public static class DragDrop extends FeatureSet {
        public DragDrop() {
            super(
                    "Dragdrop",
                    "Drag'n'drop",
                    // FIXME: text
                    "Drag'n'drop supports dragging data and components to other components or items within a component.",
                    new Feature[] {
                            //
                            new DragDropTreeSorting(), //
                            new DragDropTableTree(), //
                            new DragDropServerValidation(), //
                            new DragDropRearrangeComponents(), //
                            new DragDropHtml5FromDesktop(), //
                    });
        }
    }

    public static class Themes extends FeatureSet {

        public Themes() {
            super("Themes", "Themes", "",
                    new Feature[] { 
                    new ChameleonThemeExample(), 
                    new ReindeerThemeExample(),
                    new RunoThemeExample()
            });
        }

    }

    // ----------------------------------------------------------
    /*
     * FeatureSet implementation follows.
     */

    private static HashMap<String, Feature> pathnameToFeature;

    private final String pathname;

    private final String name;

    private final String desc;

    private final String icon = "folder.gif";

    private final Feature[] content;

    private HierarchicalContainer container = null;

    private final boolean containerRecursive = false;

    FeatureSet(String pathname, Feature[] content) {
        this(pathname, pathname, "", content);
    }

    FeatureSet(String pathname, String name, Feature[] content) {
        this(pathname, name, "", content);
    }

    FeatureSet(String pathname, String name, String desc, Feature[] content) {
        this.pathname = pathname;
        this.name = name;
        this.desc = desc;
        this.content = content;
        addFeature(this);
        if (content != null) {
            for (Feature f : content) {
                f.setParentFeature(this);
                if (f instanceof FeatureSet) {
                    continue;
                }
                addFeature(f);
            }
        }
    }

    private static void addFeature(Feature f) {
        if (pathnameToFeature == null) {
            pathnameToFeature = new HashMap<String, Feature>();
        }
        if (pathnameToFeature.containsKey(f.getFragmentName())) {
            throw new IllegalArgumentException("Duplicate pathname for "
                    + f.getFragmentName() + ": "
                    + pathnameToFeature.get(f.getFragmentName()).getClass()
                    + " / " + f.getClass());
        }
        pathnameToFeature.put(f.getFragmentName(), f);
    }

    public Feature getFeature(String pathname) {
        return pathnameToFeature.get(pathname);
    }

    public Feature[] getFeatures() {
        return content;
    }

    HierarchicalContainer getContainer(boolean recurse) {
        if (container == null || containerRecursive != recurse) {
            container = new HierarchicalContainer();
            container.addContainerProperty(PROPERTY_NAME, String.class, "");
            container.addContainerProperty(PROPERTY_DESCRIPTION, String.class,
                    "");
            // fill
            addFeatures(this, container, recurse);
        }
        return container;
    }

    private void addFeatures(FeatureSet f, HierarchicalContainer c,
            boolean recurse) {
        Feature[] features = f.getFeatures();
        for (int i = 0; i < features.length; i++) {
            Item item = c.addItem(features[i]);
            Property property = item.getItemProperty(PROPERTY_NAME);
            property.setValue(features[i].getName());
            property = item.getItemProperty(PROPERTY_DESCRIPTION);
            property.setValue(features[i].getDescription());
            if (recurse) {
                c.setParent(features[i], f);
                if (features[i] instanceof FeatureSet) {
                    addFeatures((FeatureSet) features[i], c, recurse);
                }
            }
            if (!(features[i] instanceof FeatureSet)) {
                c.setChildrenAllowed(features[i], false);
            }
        }
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public String getFragmentName() {
        return pathname;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIconName() {
        return icon;
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return null;
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return null;
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
