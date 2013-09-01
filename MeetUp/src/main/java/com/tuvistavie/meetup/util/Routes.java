package com.tuvistavie.meetup.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by daniel on 8/31/13.
 */
public enum Routes {
    GET_AUTH_TOKEN("/auth/get_token"),
    CONFIRM_USER("/auth/confirm_user"),
    EVENTS("/events");

    static final String PROTOCOL = "http";
    static final String HOST = "133.242.190.193";
    static final int PORT = 80;

    private String route;

    Routes(String route){
        this.route = route;
    }

    public String generateRoute(String query) {
        try {
            URI uri = new URI(
                    PROTOCOL,
                    null,
                    HOST,
                    PORT,
                    route,
                    query,
                    null
            );
            return uri.toASCIIString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateRoute(Map<String, String> parameters) {
        StringBuffer paramBuffer = new StringBuffer();
        for(Map.Entry<String, String> entry: parameters.entrySet()) {
            paramBuffer.append(entry.getKey() + "=" + entry.getValue());
            paramBuffer.append("&");
        }
        String s = paramBuffer.toString();
        if(s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return generateRoute(s);
    }

    public String getRoute() {
        try {
            URI uri = new URI(
                    PROTOCOL,
                    null,
                    HOST,
                    PORT,
                    route,
                    null,
                    null
            );
            return uri.toASCIIString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
