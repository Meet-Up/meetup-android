package com.tuvistavie.meetup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.tuvistavie.meetup.auth.activity.CheckMailActivity;
import com.tuvistavie.meetup.auth.model.User;
import com.tuvistavie.meetup.event.activity.TimeLineActivity;

import roboguice.activity.RoboActivity;

/**
 * Created by daniel on 9/2/13.
 */
public class MainActivity extends RoboActivity {

    @Override
    public void onCreate(Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);
        setContentView(R.layout.activity_main);

        User user = User.getInstance();
        if(user == null) {
            loginUser();
        } else {
            new CheckAuthTask().execute(user);
        }
    }

    private void loginUser() {
        Intent intent = new Intent(this, CheckMailActivity.class);
        startActivityForResult(intent, CheckMailActivity.REQUEST_CODE);
    }

    private void startApplication() {
        Intent intent = new Intent(this, TimeLineActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CheckMailActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            startApplication();
        } else {
            finish();
        }
    }


    private class CheckAuthTask extends AsyncTask<User, Void, Boolean> {
        @Override
        protected Boolean doInBackground(User... users) {
            return users[0].checkAuthentication();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success) {
                startApplication();
            } else {
                loginUser();
            }
        }
    }
}
