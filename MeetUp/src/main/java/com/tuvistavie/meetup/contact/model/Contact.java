package com.tuvistavie.meetup.contact.model;

import com.tuvistavie.meetup.App;
import com.tuvistavie.meetup.contact.util.ContactHelper;
import com.tuvistavie.meetup.event.model.PossibleDate;
import com.tuvistavie.meetup.event.model.PossibleDateCollection;
import com.tuvistavie.meetup.model.AbstractCollection;
import com.tuvistavie.meetup.model.AbstractEntity;
import com.tuvistavie.meetup.util.JSONHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    public void fromJSON(JSONObject jsonObject) {
        JSONHelper<String> stringHelper = new JSONHelper<String>();
        try {
            //this.displayName = jsonObject.getString("display_name");
            //this.phoneNumbers = helper.jsonArrayToList(jsonObject.getJSONArray("phone_numbers"));
            //this.emails = helper.jsonArrayToList(jsonObject.getJSONArray("emails"));
            this.mainEmail = jsonObject.getString("email");
            if(jsonObject.has("possible_dates")) {
                this.possibleDates = new PossibleDateCollection(jsonObject.getJSONArray("possible_dates"));
            } else {
                this.possibleDates = new PossibleDateCollection();
            }
        } catch (JSONException e) {

        }
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
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        JSONHelper<String> helper = new JSONHelper<String>();
        try {
            jsonObject.put("display_name", displayName);
            jsonObject.put("phone_numbers", helper.listToJsonArray(phoneNumbers));
            jsonObject.put("emails", helper.listToJsonArray(emails));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
}
