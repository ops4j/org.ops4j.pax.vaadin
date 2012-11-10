package com.vaadin.demo.sampler.features.treetable;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.TreeTable;

public class TreeTableTooltips extends Feature {

    @Override
    public String getName() {
        return "TreeTable, tooltips";
    }

    @Override
    public String getDescription() {
        return "You can attach a tooltip to each item or row in the TreeTable. "
                + "Hoover the mouse cursor of an item and the tooltip will be shown.";
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(TreeTable.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { TreeTableBasic.class, TreeTableCellStyles.class,
                TreeTableContextMenu.class, TreeTableSelection.class };
    }

    @Override
    public Version getSinceVersion() {
        return Version.V67;
    }

    @Override
    public Component getExample() {
        return new TreeTableTooltipsExample();
    }

}
