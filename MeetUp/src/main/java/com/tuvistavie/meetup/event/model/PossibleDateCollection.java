package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractCollection;

import org.json.JSONArray;

/**
 * Created by daniel on 9/8/13.
 */
public class PossibleDateCollection extends AbstractCollection<PossibleDate> {

    public PossibleDateCollection() {

    }

    public PossibleDateCollection(JSONArray jsonArray) {
        super(jsonArray);
    }

    @Override
    protected Class<?> getEntityClass() {
        return PossibleDate.class;
    }

    @Override
    protected String getURI() {
        return null;
    }

}
