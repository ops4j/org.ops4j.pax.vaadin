package com.vaadin.demo.sampler.features.commons;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;

@SuppressWarnings("serial")
public class PackageIcons extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getName() {
        return "Runo theme icons";
    }

    @Override
    public String getDescription() {
        return "<p>The alternative built-in <i>Runo</i> theme contains many useful free icons."
                + " The icons are not restricted to the Runo theme and "
                + " you can use them just as well in any other theme.</p>"
                + "<p>The icons are located in the Runo theme folder <tt>VAADIN/themes/runo/icons</tt>;"
                + " you can copy them to your own theme from there.</p>"
                + "<p>The icons are available in three sizes: 16x16, 32x32, and 64x64 pixels.</p>";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] {};
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { Icons.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
