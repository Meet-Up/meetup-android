package com.tuvistavie.meetup.auth.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.auth.model.User;

import java.util.regex.Pattern;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

/**
 * Created by daniel on 8/31/13.
 */
public class CheckMailActivity extends RoboActivity {

    private static final String TAG = "com.tuvistavie.meetup.uth.activity.CheckMailActivity";

    public static final String TOKEN_EXTRA = "CheckMailActivity.TOKEN_EXTRA";

    public static final int REQUEST_CODE = 10;

    private Pattern emailPattern = Patterns.EMAIL_ADDRESS;

    @InjectView(R.id.confirm_email_edit) EditText confirmEmailEdit;
    @InjectView(R.id.email_error_text) TextView emailErrorText;
    @InjectResource(R.string.email_error) String emailErrorString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_mail);

        confirmEmailEdit.setText(getEmail());
    }

    private String getEmail() {
        Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                return possibleEmail;
            }
        }
        return "";
    }

    public void onConfirmPressed(View v) {
        String emailText = confirmEmailEdit.getText().toString();
        if(emailPattern.matcher(emailText).matches()) {
            new GetTokenFileTask().execute(emailText);
        } else {
            emailErrorText.setText(emailErrorString);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EnterPinActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_mail, menu);
        return true;
    }

    private class GetTokenFileTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... emails) {
            String email = emails[0];
            return User.getRegistrationToken(email);
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "registration token received successfuly: " + result);
            Intent intent = new Intent(CheckMailActivity.this, EnterPinActivity.class);
            intent.putExtra(TOKEN_EXTRA, result);
            startActivityForResult(intent, EnterPinActivity.REQUEST_CODE);
        }
    }
    
}
