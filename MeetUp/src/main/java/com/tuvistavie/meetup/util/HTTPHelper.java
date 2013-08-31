package com.tuvistavie.meetup.util;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by daniel on 8/31/13.
 */
public final class HTTPHelper {

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
}
