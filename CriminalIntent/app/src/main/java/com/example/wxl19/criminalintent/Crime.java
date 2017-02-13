package com.example.wxl19.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by wxl19 on 2016/6/4.
 */
public class Crime {
    private UUID mId;
    private String title;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        this.mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date data) {
        this.mDate = data;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        this.mSolved = solved;
    }

    @Override
    public String toString() {
        return title;
    }
}
