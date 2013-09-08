package com.tuvistavie.meetup.event.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.contact.model.Contact;
import com.tuvistavie.meetup.contact.util.ContactListAdapter;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.model.listener.OnUpdateListener;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class ShowEventActivity extends RoboActivity {

    public static final String EVENT_EXTRA = "ShowEventActivity.event_extra";

    @InjectView(R.id.event_title_text) TextView titleTextView;
    @InjectView(R.id.event_description_text) TextView descriptionTextView;
    @InjectView(R.id.participants_number_text) TextView participantsNumberTextView;
    @InjectView(R.id.have_answered_list) ListView hasAnsweredListView;
    @InjectView(R.id.have_answered_list) ListView hasNotAnsweredListView;

    @Inject ContactListAdapter hasAnsweredAdapter;
    private ArrayAdapter<String> hasNotAnsweredAdapter;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);
        initializeEvent();
        initializeEventDisplay();

        hasAnsweredListView.setAdapter(hasAnsweredAdapter);
    }

    private void initializeEvent() {

        event = new Event();
        event.fromJSON(getIntent().getStringExtra(EVENT_EXTRA), true);
        hasAnsweredAdapter.setCollection(event.getParticipants());
        event.getParticipants().setOnUpdateListener(new OnUpdateListener() {
            @Override
            public void onUpdate(Object updatedObject) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateEventDisplay();
                    }
                });
            }
        });
        event.getParticipants().fetchInBackground();
    }

    private void updateEventDisplay() {
        int participantsNumber = event.getParticipants().getEntities().size();
        participantsNumberTextView.setText(String.valueOf(participantsNumber));
        hasAnsweredAdapter.notifyDataSetChanged();
        for(Contact contact: event.getParticipants().getEntities()) {

        }
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
