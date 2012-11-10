package com.vaadin.demo.sampler.features.dragndrop;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.ui.Tree;

public class DragDropTreeSorting extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getName() {
        return "Tree sorting using drag'n'drop";
    }

    @Override
    public String getDescription() {
        return "This example demonstrates how drag'n'drop can be used to allow a user to sort a tree."
                + " The sort is not restricted, the tree can be freely sorted in any way the user likes."
                + " Try dragging an item and dropping it on another node.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Tree.class),
                new APIResource(DropHandler.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DragDropTableTree.class,
                DragDropServerValidation.class,
                DragDropRearrangeComponents.class,
                DragDropHtml5FromDesktop.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };
    }

}
