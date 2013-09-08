package com.tuvistavie.meetup.event.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.Event;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class ShowEventActivity extends RoboActivity {

    public static final String EVENT_EXTRA = "ShowEventActivity.event_extra";

    @InjectView(R.id.event_title_text) TextView titleTextView;
    @InjectView(R.id.event_description_text) TextView descriptionTextView;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);
        event = new Event();
        event.fromJSON(getIntent().getStringExtra(EVENT_EXTRA), true);
        event.getParticipants().fetchInBackground();
        initializeEventDisplay();
    }

    private void initializeEventDisplay() {
        titleTextView.setText(event.getName());
        descriptionTextView.setText(event.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_event, menu);
        return true;
    }
    
}
