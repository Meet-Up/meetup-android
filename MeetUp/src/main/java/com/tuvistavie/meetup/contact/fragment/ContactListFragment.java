package com.tuvistavie.meetup.contact.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuvistavie.meetup.R;

import roboguice.fragment.RoboListFragment;

/**
 * Created by daniel on 9/4/13.
 */
public class ContactListFragment extends RoboListFragment {
    private static final String TAG = "com.tuvistavie.meetup.contacts.fragment.ContactListFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated called");
    }

}
