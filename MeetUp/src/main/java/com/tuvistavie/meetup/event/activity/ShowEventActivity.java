package com.tuvistavie.meetup.event.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.contact.model.Contact;
import com.tuvistavie.meetup.contact.model.ContactList;
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
    @InjectView(R.id.has_answered_list) ListView hasAnsweredListView;
    @InjectView(R.id.has_not_answered_list) ListView hasNotAnsweredListView;

    @Inject ContactListAdapter hasAnsweredAdapter;
    @Inject ContactListAdapter hasNotAnsweredAdapter;

    @Inject ContactList hasAnsweredList;
    @Inject ContactList hasNotAnsweredList;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);
        initializeEvent();
        initializeEventDisplay();

        hasAnsweredAdapter.setCollection(hasAnsweredList);
        hasNotAnsweredAdapter.setCollection(hasNotAnsweredList);
        hasAnsweredListView.setAdapter(hasAnsweredAdapter);
        hasNotAnsweredListView.setAdapter(hasNotAnsweredAdapter);
    }

    private void initializeEvent() {
        event = new Event();
        event.fromJSON(getIntent().getStringExtra(EVENT_EXTRA), true);
        event.getParticipants().setOnUpdateListener(new OnUpdateListener() {
            @Override
            public void onUpdate(Object updatedObject) {
                updateLists();
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

    private void updateLists() {
        hasAnsweredList.getEntities().clear();
        hasNotAnsweredList.getEntities().clear();
        for(Contact contact: event.getParticipants().getEntities()) {
            if(contact.getPossibleDates().getEntities().isEmpty()) {
                hasNotAnsweredList.getEntities().add(contact);
            } else {
                hasAnsweredList.getEntities().add(contact);
            }
        }
    }

    private void updateEventDisplay() {
        int participantsNumber = event.getParticipants().getEntities().size();
        String participants = getResources().getQuantityString(R.plurals.event_users_selected, participantsNumber, participantsNumber);
        participantsNumberTextView.setText(String.valueOf(participants));
        //FIXME: this could be done automatically
        hasAnsweredAdapter.notifyDataSetChanged();
        hasNotAnsweredAdapter.notifyDataSetChanged();
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
