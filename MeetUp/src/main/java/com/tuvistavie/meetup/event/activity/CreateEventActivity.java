package com.tuvistavie.meetup.event.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.inject.Inject;
import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.contact.activity.SelectContactsActivity;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.event.model.EventDateCollection;

import roboguice.inject.InjectView;
import roboguice.activity.RoboActivity;

public class CreateEventActivity extends RoboActivity {

    private static final String TAG = "com.tuvistavie.meetup.uth.activity.CheckMailActivity";

    @InjectView(R.id.event_name_text) EditText nameText;
    @InjectView(R.id.dates_number_text) TextView datesNumberText;
    @InjectView(R.id.users_number_text) TextView usersNumberText;
    @Inject EventDateCollection eventDateCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        datesNumberText.setText(getResources().getQuantityString(R.plurals.event_date_selected, 0, 0));
        usersNumberText.setText(getResources().getQuantityString(R.plurals.event_users_selected, 0, 0));
    }

    public void onAdjustTimePressed(View v) {
        Intent intent = new Intent(this, SelectDateActivity.class);
        startActivityForResult(intent, SelectDateActivity.REQUEST_CODE);
    }

    public void onSelectUsersPressed(View v) {
        Intent intent = new Intent(this, SelectContactsActivity.class);
        startActivityForResult(intent, SelectContactsActivity.REQUEST_CODE);
    }

    public void onConfirmPressed(View v) {
        String eventName = nameText.getText().toString();
        Event event = new Event(eventName, "", eventDateCollection);
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
        eventDateCollection.fromJSON(data.getStringExtra(SelectDateActivity.DATES_EXTRA));
        int count = eventDateCollection.getEntities().size();
        datesNumberText.setText(getResources().getQuantityString(R.plurals.event_date_selected, count, count));
        Log.d(TAG, "received " + eventDateCollection.getEntities().size() + " dates");
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
