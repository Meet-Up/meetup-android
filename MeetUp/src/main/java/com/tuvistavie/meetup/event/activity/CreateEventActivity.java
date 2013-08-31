package com.tuvistavie.meetup.event.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TimePicker;

import com.tuvistavie.meetup.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class CreateEventActivity extends RoboActivity {

    @InjectView(R.id.event_time_picker) TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        timePicker.setIs24HourView(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_event, menu);
        return true;
    }
    
}
