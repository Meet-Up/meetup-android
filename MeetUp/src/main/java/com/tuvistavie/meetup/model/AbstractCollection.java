package com.tuvistavie.meetup.model;

import com.tuvistavie.meetup.auth.model.User;
import com.tuvistavie.meetup.util.HTTPHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by daniel on 9/1/13.
 */
public abstract class AbstractCollection<T extends Entity> implements Collection {

    protected ArrayList<T> entities;
    protected int minFetchTimeDiff;
    private Date lastFetch;

    public AbstractCollection() {
        entities = new ArrayList<T>();
        minFetchTimeDiff = 1000;
        lastFetch = new Date(0);
    }

    @Override
    public JSONArray toJSON() {
        JSONArray jsonArray = new JSONArray();
        for(Entity e: entities) {
            jsonArray.put(e.toJSON());
        }

        return  jsonArray;
    }

    @Override
    public void fromJSON(JSONArray jsonArray) {
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T entity = createEntity(jsonObject);
                entities.add(entity);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fetch() {
        Date currentTime = new Date();
        if(currentTime.getTime() - lastFetch.getTime() <= minFetchTimeDiff) {
            return;
        }
        lastFetch = new Date();
        String uri = getURI();
        if(needsAuthentication()) {
            uri = HTTPHelper.addParameterToURI(uri, "token", User.getInstance().getToken());
        }
        JSONArray jsonArray = HTTPHelper.getJSONArray(uri);
        fromJSON(jsonArray);
    }


    // FIXME: batch save collection
    @Override
    public boolean save() {
        return true;
    }


    protected abstract Class<?> getEntityClass();
    protected abstract String getURI();


    protected T createEntity(JSONObject json) {
        try {
            return (T)getEntityClass().getConstructor(JSONObject.class).newInstance(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<T> getEntities() {
        return entities;
    }

    protected boolean needsAuthentication() {
        return true;
    }
}
