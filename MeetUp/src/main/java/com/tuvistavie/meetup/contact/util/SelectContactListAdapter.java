package com.tuvistavie.meetup.contact.util;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.tuvistavie.meetup.App;
import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.contact.model.Contact;
import com.tuvistavie.meetup.contact.model.ContactList;

/**
 * Created by daniel on 9/6/13.
 */
public class SelectContactListAdapter extends ContactListAdapter {

    @Override
    protected void updateView(View view, Contact model) {
        super.updateView(view, model);
        view.setOnClickListener(new ContactClickListener(model));
        TextView textView = (TextView)view.findViewById(R.id.contact_name_text);
        if(model.isSelected()) {
            textView.setBackgroundColor(App.getContext().getResources().getColor(R.color.light_blue));
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }
    }

    private class ContactClickListener implements View.OnClickListener {
        private Contact model;
        public ContactClickListener(Contact model) {
            this.model = model;
        }
        @Override
        public void onClick(View v) {
            model.setSelected(!model.isSelected());
            notifyDataSetChanged();
        }
    }

    public ContactList getSelected() {
        ContactList contactList = new ContactList();
        for(int i = 0; i < getCount(); i++) {
            Contact contact = getItem(i);
            if(contact.isSelected()) {
                contactList.getEntities().add(contact);
            }
        }
        return contactList;
    }
}
