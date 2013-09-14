package com.tuvistavie.meetup.event.model;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daniel on 9/14/13.
 */
public class EventTest extends TestCase {
    private Event event;

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        event = new Event();
    }

    public void testNotPersistedOnCreation() {
        assertFalse(event.isPersisted());
    }

    public void testNoIdInJSONBeforeSave() {
        JSONObject jsonObject = event.toJSONObject();
        assertFalse(jsonObject.has("id"));
    }

    public void testPersistedWhenHasId() {
        addIdToEvent();
        assertTrue(event.isPersisted());
    }

    public void testHasIdInJSONAfterSave() {
        addIdToEvent();
        JSONObject jsonObject = event.toJSONObject();
        assertTrue(jsonObject.has("id"));
    }

    private void addIdToEvent() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        event.fromJSON(jsonObject);
    }
}
