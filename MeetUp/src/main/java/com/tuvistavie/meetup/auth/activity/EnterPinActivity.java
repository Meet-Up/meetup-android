package com.tuvistavie.meetup.auth.activity;

import android.os.Bundle;

import com.tuvistavie.meetup.R;

import roboguice.activity.RoboActivity;

/**
 * Created by daniel on 8/31/13.
 */
public class EnterPinActivity extends RoboActivity {

    private static final String TAG = "com.tuvistavie.meetup.uth.activity.EnterPinActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
    }

}
