package com.tuvistavie.meetup.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 9/3/13.
 */
public class JSONHelper<T> {

    public List<T> jsonArrayToList(JSONArray array) {
        if(array == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for(int i = 0; i < array.length(); i++) {
            try {
                list.add((T)array.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public JSONArray listToJsonArray(List<T> list) {
        JSONArray array = new JSONArray();
        for(T elem: list) {
            array.put(elem);
        }
        return array;
    }
}
