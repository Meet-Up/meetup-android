package com.tuvistavie.meetup.contact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.contact.fragment.ContactListFragment;
import com.tuvistavie.meetup.contact.model.ContactList;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectFragment;

public class SelectContactsActivity extends RoboFragmentActivity {
    public static final int REQUEST_CODE = 30;

    public static final String CONTACTS_EXTRA = "SelectContactsActivity.contacts_extra";

    @InjectFragment(R.id.contact_list_fragment) ContactListFragment contactListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contacts);
    }

    public void onSavePressed(View v) {
        ContactList selectedContacts = contactListFragment.getSelectedContacts();
        Intent intent = new Intent();
        intent.putExtra(CONTACTS_EXTRA, selectedContacts.toJSON());
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_contacts, menu);
        return true;
    }
    
}
