package com.tuvistavie.meetup.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tuvistavie.meetup.App;
import com.tuvistavie.meetup.model.AbstractCollection;
import com.tuvistavie.meetup.model.listener.OnUpdateListener;

/**
 * Created by daniel on 9/4/13.
 */
public abstract class CollectionAdapter<T> extends BaseAdapter {
    protected AbstractCollection collection;
    protected Context context;

    public CollectionAdapter() {
    }

    public CollectionAdapter(Context context) {
        this.context = context;
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
    public T getItem(int position) {
        return (T)collection.getEntities().get(position);
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
    }

    public View getBaseView(int resId, View contextView, ViewGroup parent) {
        View rowView;
        if(contextView == null) {
            LayoutInflater inflater = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(resId, parent, false);
        } else {
            rowView = contextView;
        }
        return rowView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        View rowView = getBaseView(getRowResId(), contextView, parent);
        T entity = getItem(position);
        updateView(rowView, entity);
        return rowView;
    }

    protected abstract void updateView(View view, T model);
    protected abstract int getRowResId();

    public void enableAutoUpdate(final Activity activity) {
        this.collection.setOnUpdateListener(new OnUpdateListener() {
            @Override
            public void onUpdate(Object updatedObject) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
