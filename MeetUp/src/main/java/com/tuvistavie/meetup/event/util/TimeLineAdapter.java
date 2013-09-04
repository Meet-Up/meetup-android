package com.tuvistavie.meetup.event.util;

import android.view.View;
import android.widget.TextView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.util.CollectionAdapter;

/**
 * Created by daniel on 9/1/13.
 */
public class TimeLineAdapter extends CollectionAdapter<Event> {

    @Override
    protected void updateView(View view, Event model) {
        ((TextView) view.findViewById(R.id.event_name_text)).setText(model.getName());
        ((TextView) view.findViewById(R.id.event_organizer_text)).setText(model.getCreator().getUsername());
        ((TextView) view.findViewById(R.id.event_from_text)).setText(model.getStartDateString());
        ((TextView) view.findViewById(R.id.event_to_text)).setText(model.getEndDateString());
    }

    @Override
    protected int getRowResId() {
        return R.layout.timeline_row;
    }
}
