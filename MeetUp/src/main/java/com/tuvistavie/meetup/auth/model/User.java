package com.tuvistavie.meetup.auth.model;

import android.util.Log;

import com.tuvistavie.meetup.model.AbstractEntity;
import com.tuvistavie.meetup.util.HTTPHelper;
import com.tuvistavie.meetup.util.Routes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel on 8/31/13.
 */
public class User extends AbstractEntity {
    private static final String TAG = "com.tuvistavie.meetup.auth.model.User";

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
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        String uri = Routes.GET_AUTH_TOKEN.generateRoute(params);
        Log.d(TAG, "starting to get token");
        JSONObject reply = HTTPHelper.postJSONForObject(uri, params);
        try {
            String token = reply.getString("token");
            return token;
        } catch (JSONException e) {
            return null;
        }
    }
}
