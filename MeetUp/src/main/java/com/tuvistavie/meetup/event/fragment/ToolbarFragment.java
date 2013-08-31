package com.tuvistavie.meetup.event.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuvistavie.meetup.R;

import roboguice.fragment.RoboFragment;

/**
 * Created by daniel on 8/31/13.
 */
public class ToolbarFragment extends RoboFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_toolbar, container, false);
        return rootView;
    }
}
