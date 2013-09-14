package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractCollection;

import org.json.JSONArray;

/**
 * Created by daniel on 9/6/13.
 */
public class EventDateCollection extends AbstractCollection<EventDate> {
    public EventDateCollection() {
        super();
    }
    public EventDateCollection(JSONArray jsonArray) {
        super(jsonArray);
    }

    @Override
    protected Class<?> getEntityClass() {
        return EventDate.class;
    }

    @Override
    protected String getURI() {
        return null;
    }
}
