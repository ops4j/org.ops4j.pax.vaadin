package com.vaadin.demo.sampler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;
import com.vaadin.ui.Component;

/**
 * Represents one feature or sample, with associated example.
 * <p>
 * 
 * </p>
 * 
 */
@SuppressWarnings("serial")
abstract public class Feature implements Serializable {
    public static final Object PROPERTY_ICON = "Icon";
    public static final Object PROPERTY_NAME = "Name";
    public static final Object PROPERTY_DESCRIPTION = "Description";

    private static final String MSG_SOURCE_NOT_AVAILABLE = "I'm terribly sorry,"
            + " but it seems the source could not be found.\n"
            + "Please try adding the source folder to the classpath for your"
            + " server, or tell the administrator to do so!";

    private static final Object MUTEX = new Object();
    private String javaSource = null;
    private FeatureSet parentFeatureSet;

    /**
     * Gets the name of this feature. Try not to exceed 25 characters too much.
     * 
     * @return
     */
    abstract public String getName();

    /**
     * Gets the description for this feature. Should describe what the example
     * intends to showcase. May contain HTML. 100 words should be enough, and
     * about 7 rows...
     * 
     * @return the description
     */
    abstract public String getDescription();

    /**
     * Gets related resources, i.e links to resources related to the example.
     * <p>
     * Good candidates are resources used to make the example (CSS, images,
     * custom layouts), documentation links (reference manual), articles (e.g.
     * pattern description, usability discussion).
     * </p>
     * <p>
     * May return null, if the example has no related resources.
     * </p>
     * <p>
     * The name of the NamedExternalResource will be shown in the UI. <br/>
     * Note that Javadoc should be referenced via {@link #getRelatedAPI()}.
     * </p>
     * 
     * @see #getThemeBase()
     * @return related external stuff
     */
    abstract public NamedExternalResource[] getRelatedResources();

    /**
     * Gets related API resources, i.e links to javadoc of used classes.
     * <p>
     * Good candidates are Vaadin classes being demoed in the example, or other
     * classes playing an important role in the example.
     * </p>
     * <p>
     * May return null, if the example uses no interesting classes.
     * <p>
     * 
     * @return
     */
    abstract public APIResource[] getRelatedAPI();

    /**
     * Gets related Features; the classes returned should extend Feature.
     * <p>
     * Good candidates are Features similar to this one, Features using the
     * functionality demoed in this one, and Features being used in this one.
     * </p>
     * <p>
     * May return null, if no other Features are related to this one.
     * <p>
     * 
     * @return
     */
    abstract public Class<? extends Feature>[] getRelatedFeatures();

    /**
     * Gets the name of the icon for this feature, usually simpleName +
     * extension.
     * 
     * @return
     */
    public String getIconName() {
        String icon = getClass().getSimpleName() + ".gif";
        return icon;
    }

    /**
     * Get the example instance. Override if instantiation needs parameters.
     * 
     * @return
     */
    public Component getExample() {

        String className = this.getClass().getName() + "Example";
        try {
            Class<?> classObject = getClass().getClassLoader().loadClass(
                    className);
            return (Component) classObject.newInstance();
        } catch (ClassNotFoundException e) {
            return null;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }

    }

    public String getSource() {
        synchronized (MUTEX) {
            if (javaSource == null) {
                try {
                    javaSource = SourceReader.getSourceForClass(getExample()
                            .getClass());
                } catch (IOException e) {
                    System.err.println(MSG_SOURCE_NOT_AVAILABLE + " ("
                            + getFragmentName() + ")");
                    return MSG_SOURCE_NOT_AVAILABLE;
                }
            }
        }

        return javaSource;

    }

    public String getSourceHTML() {
        return getSource();
    }

    /**
     * Gets the name used when resolving the path for this feature. Usually no
     * need to override, but NOTE that this must be unique within Sampler.
     * 
     * @return
     */
    public String getFragmentName() {
        return getClass().getSimpleName();
    }

    public enum Version {
        OLD(0), BUILD(Integer.parseInt(AbstractApplicationServlet.VERSION_MAJOR
                + "" + AbstractApplicationServlet.VERSION_MINOR)), V62(62), V63(
                63), V64(64), V65(65), V66(66), V67(67);

        private final int version;

        Version(int version) {
            this.version = version;
        }

        /**
         * Checks whether this version is newer or as new as the build that it
         * is included in.
         * 
         * You can use Version.BUILD if you wish for a Feature to always appear
         * as new.
         * 
         * @return
         */
        boolean isNew() {
            return BUILD.version <= version;
        }
    }

    /**
     * Returns the Vaadin version number in which this feature was added to
     * Sampler. Usually features should only be added in major and minor
     * version, not in maintenance versions.
     * 
     * Uses int internally for easy comparison: version 6.1.4 -> 61 (maintenance
     * version number is ignored)
     * 
     * Override in each feature. Returns Version.OLD otherwise.
     * 
     * @return Version Vaadin version when this feature was added to Sampler
     */
    abstract public Version getSinceVersion();

    /**
     * Gets the base url used to reference theme resources.
     * 
     * @return
     */
    protected static final String getThemeBase() {
        return SamplerApplication.getThemeBase();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        // A feature is uniquely identified by its class name
        if (obj == null) {
            return false;
        }
        return obj.getClass() == getClass();
    }

    @Override
    public int hashCode() {
        // A feature is uniquely identified by its class name
        return getClass().hashCode();
    }

    public void setParentFeature(FeatureSet parentFeatureSet) {
        this.parentFeatureSet = parentFeatureSet;
    }

    protected Class<? extends Feature>[] getSiblingFeatures() {
        if (parentFeatureSet != null) {
            Feature[] features = parentFeatureSet.getFeatures();
            ArrayList<Class<? extends Feature>> siblingFeatureList = new ArrayList<Class<? extends Feature>>(
                    features.length - 1);
            for (Feature f : features) {
                if (f != this) {
                    siblingFeatureList.add(f.getClass());
                }
            }
            @SuppressWarnings("unchecked")
            Class<? extends Feature>[] siblingFeaturs = (Class<? extends Feature>[]) siblingFeatureList
                    .toArray(new Class<?>[siblingFeatureList.size()]);
            return siblingFeaturs;
        } else {
            return null;
        }
    }
}