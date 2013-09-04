package com.tuvistavie.meetup.contact.util;

import android.view.View;
import android.widget.TextView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.contact.model.Contact;
import com.tuvistavie.meetup.util.CollectionAdapter;

/**
 * Created by daniel on 9/4/13.
 */
public class ContactListAdapter extends CollectionAdapter<Contact> {

    @Override
    protected void updateView(View view, Contact model) {
        ((TextView)view.findViewById(R.id.contact_name_text)).setText(model.getDisplayName());
    }

    @Override
    protected int getRowResId() {
        return R.layout.contact_row;
    }
}
