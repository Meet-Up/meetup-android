package com.tuvistavie.meetup.event.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuvistavie.meetup.App;
import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.event.model.TimeLine;

/**
 * Created by daniel on 9/1/13.
 */
public class TimeLineAdapter extends BaseAdapter {
    private TimeLine timeLine;

    public TimeLineAdapter() {
        this(null);
    }

    public TimeLineAdapter(TimeLine timeLine) {
        this.timeLine = timeLine;
    }

    @Override
    public int getCount() {
        return timeLine.getEntities().size();
    }

    @Override
    public Object getItem(int position) {
        return timeLine.getEntities().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        View rowView;
        if(contextView == null) {
            LayoutInflater inflater = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.timeline_row, parent, false);
        } else {
            rowView = contextView;
        }
        Event event = timeLine.getEntities().get(position);
        ((TextView) rowView.findViewById(R.id.event_name_text)).setText(event.getName());
        ((TextView) rowView.findViewById(R.id.event_organizer_text)).setText(event.getCreator().getUsername());
        ((TextView) rowView.findViewById(R.id.event_from_text)).setText(event.getStartDateString());
        ((TextView) rowView.findViewById(R.id.event_to_text)).setText(event.getEndDateString());

        return rowView;
    }

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }
}
