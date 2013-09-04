package com.tuvistavie.meetup;

import android.app.Application;
import android.content.Context;

/**
 * Created by daniel on 8/31/13.
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        App.context = getApplicationContext();
        App.context.setTheme(R.style.AppTheme);
    }

    public static Context getContext() {
        return App.context;
    }

}
