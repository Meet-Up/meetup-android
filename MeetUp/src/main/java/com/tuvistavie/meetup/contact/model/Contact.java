package com.tuvistavie.meetup.contact.model;

import com.tuvistavie.meetup.event.model.EventDate;
import com.tuvistavie.meetup.event.model.PossibleDate;
import com.tuvistavie.meetup.event.model.PossibleDateCollection;
import com.tuvistavie.meetup.model.AbstractEntity;
import com.tuvistavie.meetup.util.JSONHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by daniel on 9/3/13.
 */
public class Contact extends AbstractEntity {

    protected String displayName;
    protected List<String> emails;
    protected String mainEmail;
    protected List<String> phoneNumbers;
    protected PossibleDateCollection possibleDates;

    public Contact(String displayName, List<String> emails, List<String> phoneNumbers) {
        this.displayName = displayName;
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        if(emails.isEmpty()) {
            this.mainEmail = "";
        } else {
            this.mainEmail = emails.get(0);
        }
    }

    public Contact(JSONObject jsonObject) {
        fromJSON(jsonObject);
    }

    @Override
    protected void convertFromJSON(JSONObject jsonObject) throws JSONException {
        super.convertFromJSON(jsonObject);
        JSONHelper<String> stringHelper = new JSONHelper<String>();
        mainEmail = jsonObject.optString("email", "");
        displayName = jsonObject.optString("display_name");
        possibleDates = new PossibleDateCollection(jsonObject.optJSONArray("possible_dates"));
        phoneNumbers = stringHelper.jsonArrayToList(jsonObject.optJSONArray("phone_numbers"));
        emails = stringHelper.jsonArrayToList(jsonObject.optJSONArray("emails"));
        this.setDisplayNameFromEmail();
    }

    private void setDisplayNameFromEmail() {
        ContactList phoneBook = new ContactList();
        phoneBook.loadFromPhoneBook();
        for(Contact contact: phoneBook.getEntities()) {
            for(String email: contact.emails) {
                if(email.equals(mainEmail)) {
                    this.displayName = contact.getDisplayName();
                    return;
                }
            }
        }
        this.displayName = "No name";
    }

    @Override
    protected JSONObject convertToJSONObject() throws JSONException {
        JSONObject jsonObject = super.convertToJSONObject();
        JSONHelper<String> helper = new JSONHelper<String>();
        jsonObject.put("display_name", displayName);
        jsonObject.putOpt("phone_numbers", helper.listToJsonArray(phoneNumbers));
        jsonObject.putOpt("emails", helper.listToJsonArray(emails));
        return jsonObject;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public PossibleDateCollection getPossibleDates() {
        return possibleDates;
    }

    public boolean isAvailable(PossibleDate possibleDate) {
        int count = 0;
        boolean[] possibleTime = possibleDate.getPossibleTime();
        for(int i = 0; i < 48; i++) {
            if(possibleTime[i]) {
                count++;
                if(count >= 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public boolean isAvailable(EventDate date) {
        for(PossibleDate p: possibleDates.getEntities()) {
            if(p.getEventDateId() == date.getId()) {
                return isAvailable(p);
            }
        }
        return false;
    }
}
