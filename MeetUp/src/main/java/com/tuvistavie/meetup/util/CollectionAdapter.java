package com.tuvistavie.meetup.util;

import android.widget.BaseAdapter;

import com.tuvistavie.meetup.model.AbstractCollection;
import com.tuvistavie.meetup.model.listener.OnUpdateListener;

/**
 * Created by daniel on 9/4/13.
 */
public abstract class CollectionAdapter<T> extends BaseAdapter {
    protected AbstractCollection collection;

    public CollectionAdapter() {
        this(null);
    }

    public CollectionAdapter(AbstractCollection collection) {
        this.setCollection(collection);
    }

    public void add(T elem) {
        this.add(elem, true);
    }

    public void add(T elem, boolean notify) {
        collection.getEntities().add(elem);
        if(notify) {
            notifyDataSetChanged();
        }
    }

    public void remove(int index) {
        this.remove(index, true);
    }

    public void remove(int index, boolean notify) {
        collection.getEntities().remove(index);
        if(notify) {
            notifyDataSetChanged();
        }
    }

    public void remove(T elem) {
        this.remove(elem, true);
    }

    public void remove(T elem, boolean notify) {
        collection.getEntities().remove(elem);
        if(notify) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return collection.getEntities().size();
    }

    @Override
    public Object getItem(int position) {
        return collection.getEntities().get(position);
    }

    public T getEntity(int position) {
        return (T)getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public AbstractCollection getCollection() {
        return collection;
    }

    public void setCollection(AbstractCollection collection) {
        this.collection = collection;
        this.collection.setOnUpdateListener(new OnUpdateListener() {
            @Override
            public void onUpdate(AbstractCollection collection) {
                notifyDataSetChanged();
            }
        });
    }
}
