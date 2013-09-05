package com.tuvistavie.meetup.event.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.util.adapter.CalendarAdapter;
import com.tuvistavie.meetup.util.DateTimeUtil;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created by daniel on 9/4/13.
 */
public class SelectDateActivity extends RoboActivity {

    public static final int REQUEST_CODE = 20;
    public static final String DATES_EXTRA = "SelectDateActivity.dates_extra";

    @InjectView(R.id.calendar_grid_view) GridView calendarGridView;
    @InjectView(R.id.previous_month_button) ImageButton previousButton;
    @InjectView(R.id.next_month_button) ImageButton nextButton;
    @InjectView(R.id.current_month_text) TextView currentMonthText;

    private CalendarAdapter calendarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        previousButton.setOnClickListener(new PreviousButtonClickListener());
        nextButton.setOnClickListener(new NextButtonClickListener());

        calendarAdapter = new CalendarAdapter(this, R.layout.calendar_cell);
        calendarGridView.setAdapter(calendarAdapter);
        calendarAdapter.registerDataSetObserver(new OnCalendarChange());
        calendarAdapter.notifyDataSetChanged();
    }

    public void onBackPressed(View v) {
        finish();
    }

    public void onSelectTimePressed(View v) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(DATES_EXTRA, calendarAdapter.getDates());
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private class PreviousButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            calendarAdapter.gotoPreviousMonth();
        }
    }

    private class NextButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            calendarAdapter.gotoNextMonth();
        }
    }

    private class OnCalendarChange extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            currentMonthText.setText(DateTimeUtil.getShortDate(calendarAdapter.getCurrentDate()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_date, menu);
        return true;
    }
    
}
