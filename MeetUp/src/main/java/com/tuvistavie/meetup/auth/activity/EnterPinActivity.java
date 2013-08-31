package com.tuvistavie.meetup.auth.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.auth.model.User;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

/**
 * Created by daniel on 8/31/13.
 */
public class EnterPinActivity extends RoboActivity {

    private static final String TAG = "com.tuvistavie.meetup.uth.activity.EnterPinActivity";

    @InjectView(R.id.pin_edit) EditText pinEdit;
    @InjectView(R.id.pin_error_view) TextView pinErrorText;
    @InjectResource(R.string.pin_error) String pinErrorString;
    @InjectExtra(CheckMailActivity.TOKEN_EXTRA) String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
    }

    public void onConfirmPressed(View v) {
        String text = pinEdit.getText().toString();
        new CheckPinTask().execute(text);
    }

    private class CheckPinTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... data) {
            String pin = data[0];
            return User.makeAuthentication(token, pin);
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result) {
                Log.d(TAG, "authentication token received successfuly");
                setResult(RESULT_OK);
                finish();
            } else {
                pinErrorText.setText(pinErrorString);
            }
        }
    }

}
