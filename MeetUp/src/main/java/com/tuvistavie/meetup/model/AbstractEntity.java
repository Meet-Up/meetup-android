package com.tuvistavie.meetup.model;

import com.tuvistavie.meetup.auth.model.User;
import com.tuvistavie.meetup.util.HTTPHelper;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by daniel on 8/31/13.
 */
public abstract class AbstractEntity implements Entity {
    protected String remoteUri;
    protected int id = -1;
    private boolean selected;

    protected HttpClient httpClient;

    public AbstractEntity(int id) {
        this.id = id;
        this.httpClient = new DefaultHttpClient();
        this.selected = false;
    }

    public AbstractEntity(JSONObject jsonObject) {
        if(jsonObject != null) {
            fromJSON(jsonObject);
        }
        this.selected = false;
    }

    public AbstractEntity() {
        this(-1);
    }

    public AbstractEntity(int id, String baseUri) {
        this(id);
        if(!baseUri.endsWith("/")) {
            baseUri += "/";
        }
        this.remoteUri = baseUri + getResourcePathUri();
    }

    protected String getResourcePathUri() {
        String path = this.getClass().getName().toLowerCase();
        if(!path.endsWith("s")) {
            path += "s";
        }
        return path + "/" + getId();
    }

    public boolean isPersisted() {
        return getId() != -1;
    }

    @Override
    public void fetch() {
        String uri = remoteUri;
        if(needsAuthentication()) {
            uri = HTTPHelper.addParameterToURI(uri, "token", User.getInstance().getToken());
        }
        fromJSON(HTTPHelper.getJSONObject(uri));
        fixEncoding();
    }

    @Override
    public boolean save() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(getEntityKey(), toJSONObject());
            if(needsAuthentication()) {
                jsonObject.put("token", User.getInstance().getToken());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(isPersisted()) {
            return HTTPHelper.putJSONForObject(getResourceUri(), jsonObject) != null;
        } else {
            return HTTPHelper.postJSONForObject(getRemoteUri(), jsonObject) != null;
        }
    }

    public String getResourceUri() {
        String path = this.remoteUri;
        if(!path.endsWith("/")) {
            path += "/";
        }
        return path + getId();
    }

    public String getRemoteUri() {
        return remoteUri;
    }

    public void setRemoteUri(String remoteUri) {
        this.remoteUri = remoteUri;
    }

    public int getId() {
        return id;
    }

    public String getEntityKey() {
        return getClass().getSimpleName().toLowerCase();
    }

    public boolean needsAuthentication() {
        return true;
    }

    @Override
    public void fromJSON(String json) {
        try {
            fromJSON(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fixEncoding() {

    }

    @Override
    public void fromJSON(JSONObject jsonObject) {
        try {
            convertFromJSON(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void convertFromJSON(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
    }


    @Override
    public String toJSON() {
        return toJSONObject().toString();
    }

    @Override
    public JSONObject toJSONObject() {
        try {
            return convertToJSONObject();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected JSONObject convertToJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if(id > 0) {
            jsonObject.put("id", id);
        }
        return jsonObject;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
