package com.tuvistavie.meetup.model;

import org.json.JSONObject;

/**
 * Created by daniel on 8/31/13.
 */
public interface JSONSerializable {
    public void fromJSON(JSONObject jsonObject);
    public JSONObject toJSON();
}
