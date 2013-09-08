package com.tuvistavie.meetup.event.util.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tuvistavie.meetup.App;
import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.activity.ShowEventActivity;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.util.CollectionAdapter;


/**
 * Created by daniel on 9/1/13.
 */
public class TimeLineAdapter extends CollectionAdapter<Event> {

    public TimeLineAdapter() {
        super();
    }

    public TimeLineAdapter(Context context) {
        super(context);
    }

    @Override
    protected void updateView(View view, Event model) {
        ((TextView) view.findViewById(R.id.event_name_text)).setText(model.getName());
        ((TextView) view.findViewById(R.id.event_organizer_text)).setText(model.getCreator().getUsername());
        ((TextView) view.findViewById(R.id.event_from_text)).setText(model.getStartDateString());
        ((TextView) view.findViewById(R.id.event_to_text)).setText(model.getEndDateString());
        view.setOnClickListener(new ViewClickedListener(model));
    }

    @Override
    protected int getRowResId() {
        return R.layout.timeline_row;
    }

    private class ViewClickedListener implements View.OnClickListener {
        private Event event;

        public ViewClickedListener(Event event) {
            this.event = event;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), ShowEventActivity.class);
            intent.putExtra(ShowEventActivity.EVENT_EXTRA, event.toJSON());
            getContext().startActivity(intent);
        }
    }
}
