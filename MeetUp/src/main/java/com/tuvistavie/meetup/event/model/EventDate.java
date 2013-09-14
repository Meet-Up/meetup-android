package com.tuvistavie.meetup.event.model;

import com.tuvistavie.meetup.contact.model.Contact;
import com.tuvistavie.meetup.contact.model.ContactList;
import com.tuvistavie.meetup.model.AbstractEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by daniel on 8/31/13.
 */
public class EventDate extends AbstractEntity implements Comparable<EventDate> {
    private Date startDateTime;
    private Date endDateTime;
    private ContactList availableUsers;

    public EventDate(long timeStamp) {
        this(new Date(timeStamp));
    }

    public EventDate(Date startDate) {
        this(startDate, new Date(startDate.getTime() + 3600));
    }

    public EventDate(Date startDate, Date endDate) {
        this.startDateTime = startDate;
        this.endDateTime = endDate;
        this.availableUsers = new ContactList();
    }

    public EventDate(JSONObject jsonObject) {
        super(jsonObject);
        availableUsers = new ContactList();
    }

    @Override
    public void convertFromJSON(JSONObject jsonObject) throws JSONException {
        super.convertFromJSON(jsonObject);
        startDateTime = new Date(jsonObject.getLong("start") * 1000);
        endDateTime = new Date(jsonObject.getLong("end") * 1000);
    }

    @Override
    public JSONObject convertToJSONObject() throws JSONException {
        JSONObject jsonDate = super.convertToJSONObject();
        jsonDate.put("start", (int)Math.ceil(startDateTime.getTime() / 1000.0));
        jsonDate.put("end", (int)Math.ceil(endDateTime.getTime() / 1000.0));
        return jsonDate;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void addAvailableUser(Contact contact) {
        availableUsers.getEntities().add(contact);
    }

    public int getAvailableUsersNumber() {
        return availableUsers.getEntities().size();
    }

    @Override
    public int compareTo(EventDate another) {
        return another.getAvailableUsersNumber() - getAvailableUsersNumber();
    }

    public void clearAvailableUsers() {
        availableUsers.getEntities().clear();
    }
}
