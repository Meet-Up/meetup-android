package com.tuvistavie.meetup.contact.activity;

import android.os.Bundle;
import android.view.Menu;

import com.tuvistavie.meetup.R;

import roboguice.activity.RoboFragmentActivity;

public class SelectContactsActivity extends RoboFragmentActivity {
    public static final int REQUEST_CODE = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contacts);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_contacts, menu);
        return true;
    }
    
}
