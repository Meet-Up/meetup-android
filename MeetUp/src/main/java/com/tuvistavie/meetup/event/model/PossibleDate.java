package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daniel on 9/8/13.
 */
public class PossibleDate extends AbstractEntity {
    private boolean[] possibleTime;

    public PossibleDate() {
        super();
    }

    public PossibleDate(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public void fromJSON(JSONObject jsonObject) {
        possibleTime = new boolean[48];
        try {
            String time = jsonObject.getString("possible_time");
            for(int i = 0; i < time.length(); i++) {
                possibleTime[i] = time.charAt(i) == '1';
            }
        } catch (JSONException e) {

        }
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        StringBuffer time = new StringBuffer();
        for(Boolean b: possibleTime) {
            time.append(b ? '1' : '0');
        }
        try {
            jsonObject.put("possible_time", time.toString());
        } catch (JSONException e) {

        }
        return jsonObject;
    }
}
