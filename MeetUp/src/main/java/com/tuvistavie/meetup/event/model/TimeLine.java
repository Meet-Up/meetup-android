package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractCollection;
import com.tuvistavie.meetup.util.Routes;

/**
 * Created by daniel on 9/1/13.
 */
public class TimeLine extends AbstractCollection<Event> {

    @Override
    protected Class<?> getEntityClass() {
        return Event.class;
    }

    @Override
    protected String getURI() {
        return Routes.EVENTS.getRoute();
    }
}
