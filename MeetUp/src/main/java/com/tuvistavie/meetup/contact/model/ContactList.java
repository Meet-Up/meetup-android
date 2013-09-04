package com.tuvistavie.meetup.contact.model;

import com.tuvistavie.meetup.model.AbstractCollection;
import com.tuvistavie.meetup.util.Routes;

/**
 * Created by daniel on 9/4/13.
 */
public class ContactList extends AbstractCollection<Contact> {
    @Override
    protected Class<?> getEntityClass() {
        return ContactList.class;
    }

    @Override
    protected String getURI() {
        return Routes.CONTACTS.getRoute();
    }
}
