package com.vaadin.demo.sampler.features.trees;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Tree;

@SuppressWarnings("serial")
public class TreeMultiSelect extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public String getName() {
        return "Tree, multiple selection";
    }

    @Override
    public String getDescription() {
        return "In this example, you can select multiple tree nodes"
                + " and delete the selected items. <br/>You can select multiple nodes by holding down the Ctrl(or Meta) key"
                + " and selecting the items.<br/> A range of items can be selected by first selecting a start item for the range and then,"
                + " by holding down the shift key, selecting the end item for the range. <br/>Finally, several ranges can be selected"
                + " by first selecting one range as described previously and then select a new starting point using the ctrl key"
                + " and the new ending point using ctrl+shift key combination.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Tree.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { TreeSingleSelect.class, TreeActions.class,
                TreeMouseEvents.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };
    }
}
