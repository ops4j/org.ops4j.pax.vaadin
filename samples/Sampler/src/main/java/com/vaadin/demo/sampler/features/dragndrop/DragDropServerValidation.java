package com.vaadin.demo.sampler.features.dragndrop;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.ServerSideCriterion;
import com.vaadin.ui.Table;

public class DragDropServerValidation extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getDescription() {
        return "In more complex cases, the browser might not have enough information to decide whether something can be dropped at a given location. "
                + "In these cases, the drag mechanism can ask the server whether dropping an item at a particular location is allowed. "
                + "Drag persons onto others with the same last name.";
    }

    @Override
    public String getName() {
        return "Drop validation, server";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(ServerSideCriterion.class),
                new APIResource(Table.class),
                new APIResource(DropHandler.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DragDropTreeSorting.class,
                DragDropTableTree.class, DragDropRearrangeComponents.class,
                DragDropHtml5FromDesktop.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };
    }

}
