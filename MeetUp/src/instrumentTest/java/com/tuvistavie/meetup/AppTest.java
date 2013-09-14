package com.tuvistavie.meetup;

import android.test.ApplicationTestCase;

/**
 * Created by daniel on 9/14/13.
 */
public class AppTest extends ApplicationTestCase<App> {

    public AppTest() {
        super(App.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testContext() {
        assertNotNull(App.getContext());
    }
}
