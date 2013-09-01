package com.tuvistavie.meetup.event.activity;

import java.util.Locale;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.auth.activity.CheckMailActivity;
import com.tuvistavie.meetup.auth.model.User;
import com.tuvistavie.meetup.event.fragment.TimelineFragment;
import com.tuvistavie.meetup.event.model.Timeline;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class TimelineActivity extends RoboFragmentActivity {

    SectionsPagerAdapter sectionsPagerAdapter;
    @InjectView(R.id.pager) ViewPager viewPager;

    @InjectResource(R.string.timeline) String timelineString;
    @InjectResource(R.string.friend_list) String friendListString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user = User.getInstance();

        if(user == null) {
            Intent intent = new Intent(this, CheckMailActivity.class);
            startActivityForResult(intent, CheckMailActivity.REQUEST_CODE);
        } else {
            initialize();
        }

    }

    private void initialize() {
        setContentView(R.layout.activity_timeline);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CheckMailActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            initialize();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }
    
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new TimelineFragment();
            Bundle args = new Bundle();
            args.putInt(TimelineFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return timelineString;
                case 1:
                    return friendListString;
            }
            return null;
        }
    }

}
