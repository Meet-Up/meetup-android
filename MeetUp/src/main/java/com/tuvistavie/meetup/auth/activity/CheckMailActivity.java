package com.tuvistavie.meetup.auth.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tuvistavie.meetup.R;

import java.util.regex.Pattern;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

/**
 * Created by daniel on 8/31/13.
 */
public class CheckMailActivity extends RoboActivity {

    private static final String TAG = "com.tuvistavie.meetup.uth.activity.CheckMailActivity";

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

    public void confirm(View v) {
        String emailText = confirmEmailEdit.getText().toString();
        if(emailPattern.matcher(emailText).matches()) {

        } else {
            emailErrorText.setText(emailErrorString);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.check_mail, menu);
        return true;
    }
    
}
