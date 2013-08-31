package com.tuvistavie.meetup.util;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by daniel on 8/31/13.
 */
public final class HTTPHelper {

    private static HttpClient httpClient = new DefaultHttpClient();

    public static void addJSONHeaders(HttpRequest request) {
        request.addHeader("Content-type", "application/json");
        request.addHeader("Accept", "application/json");
    }

    public static String getReponseContent(HttpResponse response) {
        try {
            InputStream contentStream = response.getEntity().getContent();
            StringBuffer contentBuffer = new StringBuffer();
            int c;
            while((c = contentStream.read()) != -1) {
                contentBuffer.append((char)c);
            }
            return contentBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object getJSON(String uri, boolean receivesCollection) {
        HttpGet request = new HttpGet(uri);
        addJSONHeaders(request);
        try {
            if(receivesCollection) {
                return getJSONArrayResponse(httpClient.execute(request));
            } else {
                return getJSONObjectResponse(httpClient.execute(request));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Object postOrPutJSON(String uri, JSONObject data, String requestType, boolean receivesCollection) {
        HttpEntityEnclosingRequestBase request;
        request = requestType.equals("PUT") ? new HttpPut(uri) : new HttpPost(uri);
        addJSONHeaders(request);

        try {
            request.setEntity(new ByteArrayEntity(data.toString().getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            if(receivesCollection) {
              return getJSONArrayResponse(httpClient.execute(request));
            } else {
                return getJSONObjectResponse(httpClient.execute(request));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject getJSONObjectResponse(HttpResponse response) {
        try {
            return new JSONObject(getReponseContent(response));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONArray getJSONArrayResponse(HttpResponse response) {
        try {
            return new JSONArray(getReponseContent(response));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getJSONObject(String uri) {
        return (JSONObject)getJSON(uri, false);
    }

    public static JSONArray getJSONArray(String uri) {
        return (JSONArray)getJSON(uri, true);
    }

    public static JSONObject putJSONForObject(String uri, JSONObject data) {
        return (JSONObject)postOrPutJSON(uri, data, "PUT", false);
    }

    public static JSONArray putJSONForArray(String uri, JSONObject data) {
        return (JSONArray)postOrPutJSON(uri, data, "PUT", true);
    }

    public static JSONObject postJSONForObject(String uri, JSONObject data) {
        return (JSONObject)postOrPutJSON(uri, data, "POST", false);
    }

    public static JSONArray postJSONForArray(String uri, JSONObject data) {
        return (JSONArray)postOrPutJSON(uri, data, "POST", true);
    }

}
