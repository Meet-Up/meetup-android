package com.tuvistavie.meetup.event.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.Event;
import com.tuvistavie.meetup.event.model.EventDate;
import com.tuvistavie.meetup.util.DateTimeUtil;

import java.util.Date;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class CreateEventActivity extends RoboActivity {

    @InjectView(R.id.event_name_text) EditText nameText;
    @InjectView(R.id.event_description_text) EditText descriptionText;
    @InjectView(R.id.event_time_picker) TimePicker timePicker;
    @InjectView(R.id.event_date_picker) DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        timePicker.setIs24HourView(true);
    }

    public void onAdjustTimePressed(View v) {

    }

    public void onConfirmPressed(View v) {
        String eventName = nameText.getText().toString();
        String description = descriptionText.getText().toString();
        Date date = DateTimeUtil.getDate(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        Event event = new Event(eventName, description, new EventDate(date));
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
