package com.tuvistavie.meetup.event.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.event.model.EventDate;

import java.util.Date;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class CreateEventActivity extends RoboActivity {

    @InjectView(R.id.event_name_text) EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    public void onConfirmPressed(View v) {
        String eventName = nameText.getText().toString();
        Event event = new Event(eventName, "", new EventDate(new Date()));
        new SaveEventTask().execute(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_event, menu);
        return true;
    }

    private class SaveEventTask extends AsyncTask<Event, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Event... events) {
            Event event = events[0];
            return event.save();
        }
        
        @Override
        protected void onPostExecute(Boolean result) {
            if(result) {
                finish();
            }
        }
    }
    
}
