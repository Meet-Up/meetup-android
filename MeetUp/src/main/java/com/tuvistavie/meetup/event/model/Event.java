package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractEntity;
import com.tuvistavie.meetup.util.Routes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by daniel on 8/31/13.
 */
public class Event extends AbstractEntity {
    protected String name;
    protected String description;
    protected ArrayList<EventDate> datePossibilities = new ArrayList<EventDate>();

    public Event() {
        super();
        this.remoteUri = Routes.EVENTS.getRoute();
    }

    public Event(String name, String description, EventDate... dates) {
        this();
        this.name = name;
        this.description = description;
        for(EventDate d: dates) {
            datePossibilities.add(d);
        }
    }

    public Event(JSONObject jsonObject) {
        super(jsonObject);
        this.remoteUri = Routes.EVENTS.getRoute();
    }

    public Event(int id) {
        super(id);
        this.remoteUri = Routes.EVENTS.getRoute();
    }

    @Override
    public void fromJSON(JSONObject jsonObject) {
        try {
            id = jsonObject.getInt("id");
            name = jsonObject.getString("name");
            description = jsonObject.getString("description");
            JSONArray dates = jsonObject.getJSONArray("dates");
            for(int i = 0; i < dates.length(); i++) {
                datePossibilities.add(new EventDate(dates.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonEvent = new JSONObject();
        JSONArray jsonDates = new JSONArray();
        try {
            jsonEvent.put("name", name);
            jsonEvent.put("description", description);
            for(EventDate date : datePossibilities) {
                jsonDates.put(date.toJSON());
            }
            jsonEvent.put("event_dates_attributes", jsonDates);
            return jsonEvent;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
