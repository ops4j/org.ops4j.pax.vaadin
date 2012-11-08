package com.vaadin.demo.sampler.features.treetable;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.TreeTable;

public class TreeTableBasic extends Feature {

    @Override
    public String getName() {
        return "TreeTable, basic";
    }

    @Override
    public String getDescription() {
        return "The TreeTable is an extended Table component that can show hierarchical"
                + " structures in its first column. Users can show or hide children from a small"
                + " icon before the actual column value in the first column.";
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(TreeTable.class) };
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { TreeTableCellStyles.class,
                TreeTableContextMenu.class, TreeTableSelection.class,
                TreeTableTooltips.class };
    }

    @Override
    public Version getSinceVersion() {
        return Version.V67;
    }

    @Override
    public Component getExample() {
        return new TreeTableBasicExample();
    }
}
