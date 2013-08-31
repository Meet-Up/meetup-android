package com.tuvistavie.meetup.auth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tuvistavie.meetup.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectExtra;

/**
 * Created by daniel on 8/31/13.
 */
public class EnterPinActivity extends RoboActivity {

    private static final String TAG = "com.tuvistavie.meetup.uth.activity.EnterPinActivity";

    @InjectExtra(CheckMailActivity.TOKEN_EXTRA) String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
        Log.d(TAG, token);
    }

}
