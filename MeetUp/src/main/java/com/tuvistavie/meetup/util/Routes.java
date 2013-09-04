package com.tuvistavie.meetup.util;

import com.tuvistavie.meetup.Config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by daniel on 8/31/13.
 */
public enum Routes {
    GET_AUTH_TOKEN("/auth/get_token"),
    CONFIRM_USER("/auth/confirm_user"),
    CHECK_AUTH("/auth/check"),
    EVENTS("/events"),
    CONTACTS("/contacts");

    private String route;

    Routes(String route){
        this.route = route;
    }

    public String generateRoute(String query) {
        try {
            URI uri = new URI(
                    Config.PROTOCOL,
                    null,
                    Config.HOST,
                    Config.PORT,
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
        return generateRoute((String)null);
    }
}
