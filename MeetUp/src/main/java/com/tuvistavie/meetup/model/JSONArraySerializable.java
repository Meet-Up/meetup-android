package com.tuvistavie.meetup.model;

import org.json.JSONArray;

/**
 * Created by daniel on 9/1/13.
 */
public interface JSONArraySerializable {
    public void fromJSON(JSONArray jsonArray);
    public JSONArray toJSON();
}
