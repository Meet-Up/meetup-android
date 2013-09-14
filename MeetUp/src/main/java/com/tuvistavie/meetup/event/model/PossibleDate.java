package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daniel on 9/8/13.
 */
public class PossibleDate extends AbstractEntity {
    private boolean[] possibleTime;
    private int eventDateId;
    private int userId;

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
            eventDateId = jsonObject.getInt("event_date_id");
            userId = jsonObject.getInt("user_id");
        } catch (JSONException e) {

        }
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        StringBuilder time = new StringBuilder();
        for(Boolean b: possibleTime) {
            time.append(b ? '1' : '0');
        }
        try {
            jsonObject.put("possible_time", time.toString());
            jsonObject.put("event_date_id", eventDateId);
            jsonObject.put("user_id", userId);
        } catch (JSONException e) {

        }
        return jsonObject;
    }

    public boolean[] getPossibleTime() {
        return possibleTime;
    }

    public int getEventDateId() {
        return eventDateId;
    }


}
