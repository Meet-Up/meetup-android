package com.tuvistavie.meetup.event.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.util.CollectionAdapter;

/**
 * Created by daniel on 9/1/13.
 */
public class TimeLineAdapter extends CollectionAdapter<Event> {

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        View rowView = getBaseView(R.layout.timeline_row, contextView, parent);
        Event event = getEntity(position);
        ((TextView) rowView.findViewById(R.id.event_name_text)).setText(event.getName());
        ((TextView) rowView.findViewById(R.id.event_organizer_text)).setText(event.getCreator().getUsername());
        ((TextView) rowView.findViewById(R.id.event_from_text)).setText(event.getStartDateString());
        ((TextView) rowView.findViewById(R.id.event_to_text)).setText(event.getEndDateString());

        return rowView;
    }
}
