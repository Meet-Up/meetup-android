package com.tuvistavie.meetup.event.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.event.model.EventDate;
import com.tuvistavie.meetup.event.model.EventDateCollection;

import java.util.Date;

import roboguice.inject.InjectView;
import roboguice.activity.RoboActivity;

public class CreateEventActivity extends RoboActivity {

    private static final String TAG = "com.tuvistavie.meetup.uth.activity.CheckMailActivity";

    public static final String DATES_EXTRA = "CreateEventActivity.dates_extra";

    @InjectView(R.id.event_name_text) EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    public void onAdjustTimePressed(View v) {
        Intent intent = new Intent(this, SelectDateActivity.class);
        startActivityForResult(intent, SelectDateActivity.REQUEST_CODE);
    }

    public void onConfirmPressed(View v) {
        String eventName = nameText.getText().toString();
        Event event = new Event(eventName, "", new EventDateCollection());
        new SaveEventTask().execute(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == SelectDateActivity.REQUEST_CODE) {
                handleSelectDateReturn(data);
            }
        }
    }

    private void handleSelectDateReturn(Intent data) {
        EventDateCollection eventDateCollection = new EventDateCollection();
        eventDateCollection.fromJSON(data.getStringExtra(SelectDateActivity.DATES_EXTRA));
        Log.d(TAG, "received " + eventDateCollection.getEntities().size() + " dates");
    }

    private void handleSelectTimeReturn(Intent data) {

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
