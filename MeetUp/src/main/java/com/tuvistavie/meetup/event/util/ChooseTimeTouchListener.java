package com.tuvistavie.meetup.event.util;

import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import com.tuvistavie.meetup.event.model.TimeCellContainer;

/**
 * Created by daniel on 9/5/13.
 */
public class ChooseTimeTouchListener implements View.OnTouchListener {
    private boolean isSelecting;
    private TimeCellContainer timeCellContainer;
    private int startPosition;

    public ChooseTimeTouchListener(TimeCellContainer timeCellContainer) {
        this.timeCellContainer = timeCellContainer;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        GridView gridView = (GridView)v;
        int position = gridView.pointToPosition((int)event.getX(), (int)event.getY());
        if(position == -1) {
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            isSelecting = !timeCellContainer.getAt(position);
            startPosition = position;
        } else if(event.getAction() == MotionEvent.ACTION_CANCEL) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
            return true;
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
            timeCellContainer.setRange(startPosition, position, isSelecting);
        }

        return false;
    }


}
