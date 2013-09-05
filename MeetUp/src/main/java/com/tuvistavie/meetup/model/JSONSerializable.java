package com.tuvistavie.meetup.model;

/**
 * Created by daniel on 9/6/13.
 */
public interface JSONSerializable {
    public String toJSON();
    public void fromJSON(String json);
}
