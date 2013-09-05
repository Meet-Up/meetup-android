package com.tuvistavie.meetup.event.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.tuvistavie.meetup.R;
import com.tuvistavie.meetup.event.model.EventDateCollection;
import com.tuvistavie.meetup.event.model.TimeCellContainer;
import com.tuvistavie.meetup.event.util.ChooseTimeTouchListener;
import com.tuvistavie.meetup.event.util.adapter.SelectTimeAdapter;
import com.tuvistavie.meetup.util.DateTimeUtil;
import com.tuvistavie.meetup.view.ExpandableGridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SelectTimeActivity extends Activity {
    public static final int REQUEST_CODE = 21;
    public static final String DATES_EXTRA = "SelectTimeActivity.dates_extra";

    public static final int CELLS_PER_DAY = 48;
    public static final int DAYS_PER_PAGE = 4;

    private ExpandableGridView timeCellsGridView;
    private SelectTimeAdapter timeCellsAdapter;
    private ArrayAdapter<String> datesAdapter;
    private GridView datesGridView;
    private TimeCellContainer timeCellContainer;

    private long[] datesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        datesArray = getIntent().getLongArrayExtra(SelectDateActivity.DATES_EXTRA);
        timeCellContainer = new TimeCellContainer(datesArray.length);
        initViews();
    }

    private void initViews() {
        initTimeCellsGridView();
        initDatesGrid();
        initTimesView();
    }

    public void onSavePressed(View v) {
        EventDateCollection dates = timeCellContainer.toEventDateCollection(datesArray);
        Intent intent = new Intent();
        intent.putExtra(DATES_EXTRA, dates.toJSONArray().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void initTimesView() {
        ExpandableGridView timesGridView = (ExpandableGridView)findViewById(R.id.times_list_view);
        timesGridView.setExpanded(true);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, R.layout.time_info, generateTimes());
        timesGridView.setAdapter(timeAdapter);
    }

    private String getTextForColumn(int column) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(datesArray[timeCellContainer.getColumnNumberAt(column)]));
        return (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
    }

    private void initTimeCellsGridView() {
        timeCellsAdapter = new SelectTimeAdapter(this, timeCellContainer);
        timeCellsGridView = (ExpandableGridView)findViewById(R.id.choose_time_grid_view);
        timeCellsGridView.setExpanded(true);
        timeCellsGridView.setAdapter(timeCellsAdapter);
        timeCellsGridView.setOnTouchListener(new ChooseTimeTouchListener(timeCellContainer));
        updateTimeCellsGrid();
    }

    private void initDatesGrid() {
        datesAdapter = new ArrayAdapter<String>(this, R.layout.time_info);
        datesGridView = (GridView)findViewById(R.id.dates_grid_view);
        datesGridView.setAdapter(datesAdapter);
        updateDatesGrid();
    }

    private void updateDatesGrid() {
        datesAdapter.setNotifyOnChange(false);
        datesGridView.setNumColumns(timeCellContainer.getDisplayedColumnsNumber());
        datesAdapter.clear();
        for(int i = 0; i < timeCellContainer.getDisplayedColumnsNumber(); i++) {
            datesAdapter.add(getTextForColumn(i));
        }
        datesAdapter.setNotifyOnChange(false);
        datesAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> generateTimes() {
        ArrayList<String> timeStrings = new ArrayList<String>();
        int intervalInMinutes = (int)(24.0 * 60 / CELLS_PER_DAY);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        for(int i = 0; i < CELLS_PER_DAY; i++) {
            timeStrings.add(DateTimeUtil.formatTime(c.getTime()));
            c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + intervalInMinutes);
        }
        return timeStrings;
    }

    private void updateTimeCellsGrid() {
        timeCellsGridView.setNumColumns(timeCellContainer.getDisplayedColumnsNumber());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_time, menu);
        return true;
    }
}
