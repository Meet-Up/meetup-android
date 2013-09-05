package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daniel on 9/1/13.
 */
public class Participant extends AbstractEntity {
    private String username;

    public Participant(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public void fromJSON(JSONObject jsonObject) {
        try {
            username = jsonObject.getString("username");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject toJSONObject() {
        return null;
    }

    public String getUsername() {
        return username;
    }
}
