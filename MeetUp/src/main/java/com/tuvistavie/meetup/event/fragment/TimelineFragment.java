package com.tuvistavie.meetup.event.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.inject.Inject;
import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.Timeline;
import com.tuvistavie.meetup.event.util.TimelineAdapter;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by daniel on 8/31/13.
 */
public class TimelineFragment extends RoboFragment {
    public static final String ARG_SECTION_NUMBER = "section_number";

    @InjectView(R.id.time_list_view) ListView listView;
    @Inject TimelineAdapter adapter;
    @Inject Timeline timeline;

    //@InjectExtra(ARG_SECTION_NUMBER) int argSection;

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
        adapter.setTimeline(timeline);
        listView.setAdapter(adapter);
        new LoadTimelineTask().execute();
    }

    private class LoadTimelineTask extends AsyncTask<Void, Void, Timeline> {
        @Override
        protected Timeline doInBackground(Void... params) {
            timeline.fetch();
            return timeline;
        }

        @Override
        protected void onPostExecute(Timeline timeline) {
            adapter.notifyDataSetChanged();
        }
    }
}
