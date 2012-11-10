package com.vaadin.demo.sampler.features.trees;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;

public class TreeKeyboardNavigation extends Feature {

    @Override
    public String getDescription() {
        return "You can use the keyboard to view and edit the tree selection. To move in the tree use the up, "
                + "down,left and right arrow keys. The up and down keys moves up and down in the tree while the "
                + "left and right keys expands and collapses the tree branches. <br/>"
                + "By holding the CTRL key down you can move the selection head up and down "
                + "and by pressing SPACE while holding the CTRL key down you can select multiple items. To select a range "
                + "of items hold down SHIFT and move up or down using the arrow keys.<br>"
                + "To expand a branch use the right arrow key and to collapse a branch use the left arrow key.";
    }

    @Override
    public String getName() {
        return "Tree, keyboard navigation";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Tree.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { TreeSingleSelect.class, TreeActions.class,
                TreeMouseEvents.class, TreeMultiSelect.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public Component getExample() {
        return new FocusedTreeExample();
    }

    private class FocusedTreeExample extends TreeMultiSelectExample {
        @Override
        public void attach() {
            super.attach();
            Tree t = (Tree) getComponent(1);
            t.focus();
        }
    }

}
