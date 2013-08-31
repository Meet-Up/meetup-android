package com.tuvistavie.meetup;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.tuvistavie.meetup.auth.activity.CheckMailActivity;
import com.tuvistavie.meetup.auth.model.User;

/**
 * Created by daniel on 8/31/13.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user = User.getInstance();

        if(user == null) {
            Intent intent = new Intent(this, CheckMailActivity.class);
            startActivityForResult(intent, R.id.check_mail_requestcode);
        } else {
            initialize();
        }

    }

    private void initialize() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == R.id.check_mail_requestcode && resultCode == RESULT_OK) {
            initialize();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
