package com.lemo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by wxl19 on 2017/2/27.
 */

public class Crime {

    private UUID mId;

    private String mTitle;

    private Date mDate;

    private boolean mSolved;

    public Crime() {
        this.mId = UUID.randomUUID();
        this.mDate = new Date();
    }


    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    public Date getDate() {
        return mDate;
    }

    public String getDateStr(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(getDate());
        return dateStr;
    }

    public String getTimeStr(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(getDate());
    }

    public void setDate(Date date) {
        mDate = date;
    }


    public boolean isSolved() {
        return mSolved;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
