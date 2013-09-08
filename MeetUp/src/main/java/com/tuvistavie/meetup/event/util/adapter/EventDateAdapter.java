package com.tuvistavie.meetup.event.util.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.EventDate;
import com.tuvistavie.meetup.util.CollectionAdapter;
import com.tuvistavie.meetup.util.DateTimeUtil;

/**
 * Created by daniel on 9/8/13.
 */
public class EventDateAdapter extends CollectionAdapter<EventDate> {

    public EventDateAdapter() {
        super();
    }
    public EventDateAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCount() {
        int totalCount = super.getCount();
        for(int i = 0; i < totalCount; i++) {
            if(getItem(i).getAvailableUsersNumber() == 0) {
                return i;
            }
        }
        return totalCount;
    }

    @Override
    protected void updateView(View view, EventDate model) {
        String text = DateTimeUtil.formatDateTime(model.getStartDateTime()) + ": " + model.getAvailableUsersNumber();
        ((TextView)view.findViewById(R.id.date_text_view)).setText(text);
    }

    @Override
    protected int getRowResId() {
        return R.layout.date_row;
    }
}
