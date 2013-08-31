package com.tuvistavie.meetup.auth.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;

import com.tuvistavie.meetup.App;
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

    private static User instance;

    protected String email;
    protected String token;

    private User() {
    }

    public static User getInstance() {
        if(instance == null) {
            instance = loadUserFromPrefs();
        }
        return instance;
    }

    private void saveToPrefs() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        preferences.edit().putString("user", toJSON().toString()).commit();
    }

    private static User loadUserFromPrefs() {
        User user = null;
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
            String userString = preferences.getString("user", null);
            if(userString == null) {
                return null;
            }
            user = new User();
            user.fromJSON(new JSONObject(userString));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void fromJSON(JSONObject jsonObject) {
        try {
            email = jsonObject.getString("email");
            token = jsonObject.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String getRegistrationToken(String email) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        String uri = Routes.GET_AUTH_TOKEN.getRoute();
        Log.d(TAG, "starting to get registration token");
        JSONObject reply = HTTPHelper.postJSONForObject(uri, params);
        try {
            String token = reply.getString("token");
            return token;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean makeAuthentication(String registrationToken, String pinCode) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", registrationToken);
        params.put("pin_code", pinCode);
        String uri = Routes.CONFIRM_USER.getRoute();
        Log.d(TAG, "starting to get authentication token");
        JSONObject reply = HTTPHelper.postJSONForObject(uri, params);
        User user = new User();
        try {
            user.fromJSON(reply.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(user.token == null || user.token.isEmpty()) {
            return false;
        } else {
            user.saveToPrefs();
            return true;
        }
    }
}
