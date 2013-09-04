package com.tuvistavie.meetup.event.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.inject.Inject;
import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.TimeLine;
import com.tuvistavie.meetup.event.util.TimeLineAdapter;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by daniel on 8/31/13.
 */
public class TimeLineFragment extends RoboFragment {
    private static final String TAG = "com.tuvistavie.meetup.event.fragment.TimeLineFragment";

    @InjectView(R.id.time_list_view) ListView listView;
    @Inject TimeLineAdapter adapter;
    @Inject TimeLine timeLine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setCollection(timeLine);
        adapter.enableAutoUpdate(getActivity());
        listView.setAdapter(adapter);

        timeLine.fetchInBackground();
    }
}
