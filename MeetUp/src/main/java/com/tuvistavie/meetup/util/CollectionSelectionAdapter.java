package com.tuvistavie.meetup.util;

import com.tuvistavie.meetup.model.AbstractCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 9/6/13.
 */
public abstract class CollectionSelectionAdapter<T> extends CollectionAdapter<T> {
    private ArrayList<Boolean> selectionFlag;

    protected void setSelected(int position, boolean b) {
        selectionFlag.set(position, b);
    }

    protected boolean isSelected(int position) {
        return selectionFlag.get(position);
    }

    public List<T> getSelected() {
        List<T> selectedItems = new ArrayList<T>();
        for(int i = 0; i < getCount(); i++) {
            if(selectionFlag.get(i)) {
                selectedItems.add(getItem(i));
            }
        }
        return selectedItems;
    }

    @Override
    public void setCollection(AbstractCollection collection) {
        super.setCollection(collection);
        selectionFlag.clear();
        for(int i = 0; i < collection.getEntities().size(); i++) {
            selectionFlag.add(false);
        }
    }
}
