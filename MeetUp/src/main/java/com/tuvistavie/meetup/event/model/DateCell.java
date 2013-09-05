package com.tuvistavie.meetup.event.model;

import java.util.Date;

/**
 * Created by daniel on 9/4/13.
 */
public class DateCell {
    private Date date;
    private boolean isSelected;

    public DateCell(Date date) {
        this.date = date;
        this.isSelected = false;
    }

    public DateCell(long date) {
        this(new Date(date));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
