package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.contact.model.Contact;
import com.tuvistavie.meetup.contact.model.ContactList;
import com.tuvistavie.meetup.model.AbstractEntity;
import com.tuvistavie.meetup.util.DateTimeUtil;
import com.tuvistavie.meetup.util.Routes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

/**
 * Created by daniel on 8/31/13.
 */
public class Event extends AbstractEntity {
    protected String name;
    protected String description;
    protected EventDateCollection eventDateCollection;
    protected ContactList participants;

    protected Participant creator;

    public Event() {
        super();
        this.remoteUri = Routes.EVENTS.getRoute();
        this.participants = new ContactList();
        this.eventDateCollection = new EventDateCollection();
    }

    public Event(String name, String description, EventDateCollection eventDateCollection, ContactList participants) {
        this();
        this.name = name;
        this.description = description;
        this.eventDateCollection = eventDateCollection;
        this.participants = participants;
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
    public void convertFromJSON(JSONObject jsonObject) throws JSONException {
        super.convertFromJSON(jsonObject);
        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
        if(jsonObject.has("creator")) {
            creator = new Participant(jsonObject.getJSONObject("creator"));
        }
        eventDateCollection = new EventDateCollection(jsonObject.optJSONArray("event_dates"));
        participants = new ContactList(jsonObject.optJSONArray("participants"));
        participants.setUri(Routes.PARTICIPANTS.getRoute(id));
    }

    @Override
    public void fixEncoding() {
        try {
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            description = new String(description.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected JSONObject convertToJSONObject() throws JSONException {
        JSONObject jsonEvent = super.convertToJSONObject();
        jsonEvent.put("name", name);
        jsonEvent.put("description", description);
        jsonEvent.put("event_dates", eventDateCollection.toJSONArray());
        JSONArray users = new JSONArray();
        for(Contact contact: participants.getEntities()) {
            List<String> emails = contact.getEmails();
            if(!emails.isEmpty()) {
                users.put(emails.get(0));
            }
        }
        jsonEvent.put("users", users);
        return jsonEvent;
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
        return DateTimeUtil.formatDateTime(eventDateCollection.getEntities().get(0).getStartDateTime());
    }

    public String getEndDateString() {
        if(eventDateCollection.getEntities().isEmpty()) {
            return "";
        }
        return DateTimeUtil.formatDateTime(eventDateCollection.getEntities().get(0).getEndDateTime());
    }

    public ContactList getParticipants() {
        return participants;
    }

    public void setParticipants(ContactList participants) {
        this.participants = participants;
    }

    // FIXME: risk to add same user several times
    public EventDateCollection getBestDates() {
        for(EventDate date: eventDateCollection.getEntities()) {
            date.clearAvailableUsers();
        }
        for(Contact contact: participants.getEntities()) {
            for(EventDate date: eventDateCollection.getEntities()) {
                if(contact.isAvailable(date)) {
                    date.addAvailableUser(contact);
                }
            }
        }
        Collections.sort(eventDateCollection.getEntities());
        return eventDateCollection;
    }
}
