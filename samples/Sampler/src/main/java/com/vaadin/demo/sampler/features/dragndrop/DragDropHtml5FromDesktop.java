package com.vaadin.demo.sampler.features.dragndrop;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.WrapperTransferable;
import com.vaadin.ui.Html5File;

public class DragDropHtml5FromDesktop extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getDescription() {
        return "On browsers supporting HTML5 style drag and drop several data flovours can "
                + "be dropped from desktop applications to Vaadin application. Firefox from version 3.6 even "
                + "support dragging files from the client file system to a Vaadin application. "
                + "With Firefox, try dropping an image file from the desktop to the drop box, else you "
                + "must retain to dragging text from your favorite word processor. ";
    }

    @Override
    public String getName() {
        return "Drag from desktop applications";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(DragAndDropWrapper.class),
                new APIResource(WrapperTransferable.class),
                new APIResource(Html5File.class),
                new APIResource(DropHandler.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { DragDropTreeSorting.class,
                DragDropTableTree.class, DragDropServerValidation.class,
                DragDropRearrangeComponents.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

}
