package com.tuvistavie.meetup.model;

import com.tuvistavie.meetup.util.HTTPHelper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Created by daniel on 8/31/13.
 */
public abstract class AbstractEntity implements Entity, JSONSerializable {
    private String remoteUri;
    protected int id;

    protected HttpClient httpClient;

    public AbstractEntity(int id) {
        this.id = id;
        this.httpClient = new DefaultHttpClient();
    }

    public AbstractEntity(JSONObject jsonObject) {
        fromJSON(jsonObject);
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

    @Override
    public void fetch() {
        fromJSON(HTTPHelper.getJSON(remoteUri));
    }

    @Override
    public boolean save() {
        HttpEntityEnclosingRequestBase request;
        if(getId() == -1) {
            request = new HttpPost(getRemoteUri());
        } else {
            request = new HttpPut(getResourceUri());
        }
        HTTPHelper.addJSONHeaders(request);
        try {
            request.setEntity(new ByteArrayEntity(toJSON().toString().getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(request);
            return response.getStatusLine().getStatusCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
}
