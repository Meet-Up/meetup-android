package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.model.AbstractEntity;
import com.tuvistavie.meetup.util.DateTimeUtil;
import com.tuvistavie.meetup.util.Routes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daniel on 8/31/13.
 */
public class Event extends AbstractEntity {
    protected String name;
    protected String description;
    protected EventDateCollection eventDateCollection;

    protected Participant creator;

    public Event() {
        super();
        this.remoteUri = Routes.EVENTS.getRoute();
    }

    public Event(String name, String description, EventDateCollection eventDateCollection) {
        this();
        this.name = name;
        this.description = description;
        this.eventDateCollection = eventDateCollection;
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
            creator = new Participant(jsonObject.getJSONObject("creator"));
            this.eventDateCollection = new EventDateCollection();
            eventDateCollection.fromJSON(jsonObject.getJSONArray("event_dates"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonEvent = new JSONObject();
        try {
            jsonEvent.put("name", name);
            jsonEvent.put("description", description);
            jsonEvent.put("event_dates_attributes", eventDateCollection.toJSONArray());
            return jsonEvent;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Participant getCreator() {
        return creator;
    }

    public String getStartDateString() {
        if(eventDateCollection.getEntities().isEmpty()) {
            return "";
        }
        EventDate e = eventDateCollection.getEntities().get(0);
        return DateTimeUtil.formatDate(eventDateCollection.getEntities().get(0).getStartDateTime());
    }

    public String getEndDateString() {
        if(eventDateCollection.getEntities().isEmpty()) {
            return "";
        }
        return DateTimeUtil.formatDate(eventDateCollection.getEntities().get(0).getEndDateTime());
    }
}
