package com.vaadin.demo.sampler.features.dragndrop;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.ExampleUtilResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.ClientSideCriterion;
import com.vaadin.event.dd.acceptcriteria.SourceIs;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TargetItemAllowsChildren;

public class DragDropTableTree extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getDescription() {
        return "This example demonstrates how drag'n'drop can be used to move data between different components. "
                + "Try dragging hardware items or categories from the tree to the table or table rows onto categories in the tree. "
                + "Each component accepts data from the other one but not from itself, and the tree only accepts items dropped onto the correct category. "
                + "Validation of allowed drop targets is performed on the client side, without requests to the server.";
    }

    @Override
    public String getName() {
        return "Drag items between tree and table";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Tree.class),
                new APIResource(Table.class),
                new APIResource(DropHandler.class),
                new APIResource(ClientSideCriterion.class),
                new APIResource(SourceIs.class),
                new APIResource(TargetItemAllowsChildren.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DragDropTreeSorting.class,
                DragDropServerValidation.class,
                DragDropRearrangeComponents.class,
                DragDropHtml5FromDesktop.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new ExampleUtilResource() };
    }

}
