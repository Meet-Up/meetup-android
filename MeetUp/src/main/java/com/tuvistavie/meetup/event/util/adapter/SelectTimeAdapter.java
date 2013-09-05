package com.tuvistavie.meetup.event.util.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.activity.SelectTimeActivity;
import com.tuvistavie.meetup.event.model.TimeCellContainer;
import com.tuvistavie.meetup.model.listener.OnUpdateListener;

/**
 * Created by daniel on 9/5/13.
 */
public class SelectTimeAdapter extends BaseAdapter {

    private TimeCellContainer timeCellContainer;

    private Context context;

    public SelectTimeAdapter(Context context, TimeCellContainer timeCellContainer) {
        this.context = context;
        this.timeCellContainer = timeCellContainer;
        timeCellContainer.setOnUpdateListener(new OnUpdateListener() {
            @Override
            public void onUpdate(Object udpatedObject) {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getCount() {
        return timeCellContainer.getDisplayedColumnsNumber() * SelectTimeActivity.CELLS_PER_DAY;
    }

    @Override
    public Boolean getItem(int position) {
        return timeCellContainer.getAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.date_time_cell, parent, false);
        } else {
            cellView = convertView;
        }

        if(getItem(position)) {
            cellView.setBackgroundColor(getContext().getResources().getColor(R.color.light_blue));
        } else {
            cellView.setBackgroundColor(Color.WHITE);
        }

        return cellView;
    }

    public Context getContext() {
        return context;
    }

}
