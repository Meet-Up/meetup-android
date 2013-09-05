package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractCollection;

/**
 * Created by daniel on 9/6/13.
 */
public class EventDateCollection extends AbstractCollection<EventDate> {
    @Override
    protected Class<?> getEntityClass() {
        return EventDateCollection.class;
    }

    @Override
    protected String getURI() {
        return null;
    }
}
