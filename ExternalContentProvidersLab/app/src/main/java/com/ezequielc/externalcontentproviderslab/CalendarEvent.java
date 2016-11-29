package com.ezequielc.externalcontentproviderslab;

/**
 * Created by student on 11/29/16.
 */

public class CalendarEvent {
    private long mID;
    private String mTitle;
    private long mDate;

    public CalendarEvent(long id, String title, long date) {
        mID = id;
        mTitle = title;
        mDate = date;
    }

    public long getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public long getDate() {
        return mDate;
    }
}
