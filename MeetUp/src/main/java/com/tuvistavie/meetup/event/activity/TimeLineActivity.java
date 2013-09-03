package com.tuvistavie.meetup.event.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.fragment.TimeLineFragment;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class TimeLineActivity extends RoboFragmentActivity {

    SectionsPagerAdapter sectionsPagerAdapter;
    @InjectView(R.id.pager) ViewPager viewPager;

    @InjectResource(R.string.timeLine) String timeLineString;
    @InjectResource(R.string.friend_list) String friendListString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();
    }

    private void initialize() {
        setContentView(R.layout.activity_timeline);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
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
            Fragment fragment = new TimeLineFragment();
            Bundle args = new Bundle();
            args.putInt(TimeLineFragment.ARG_SECTION_NUMBER, position + 1);
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
                    return timeLineString;
                case 1:
                    return friendListString;
            }
            return null;
        }
    }
}
