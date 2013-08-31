package com.tuvistavie.meetup.auth.model;

import com.tuvistavie.meetup.model.AbstractEntity;
import org.json.JSONObject;

/**
 * Created by daniel on 8/31/13.
 */
public class User extends AbstractEntity {
    protected String email;

    public User(String email) {
        this.email = email;
    }

    @Override
    public void fromJSON(JSONObject jsonObject) {

    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    public String getRegistrationToken() {
        return "";
    }
}
