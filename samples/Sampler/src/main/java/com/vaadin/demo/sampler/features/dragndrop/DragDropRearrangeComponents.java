package com.vaadin.demo.sampler.features.dragndrop;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.ui.DragAndDropWrapper;

public class DragDropRearrangeComponents extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getDescription() {
        return "In addition to arbitrary data items, whole components can also be dragged. "
                + "Here, the order of various components in a layout can be rearranged by dragging the components.";
    }

    @Override
    public String getName() {
        return "Drag components";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(DragAndDropWrapper.class),
                new APIResource(DropHandler.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DragDropTreeSorting.class,
                DragDropTableTree.class, DragDropServerValidation.class,
                DragDropHtml5FromDesktop.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
