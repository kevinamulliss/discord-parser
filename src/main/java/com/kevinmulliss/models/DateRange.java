package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DateRange {
    @SerializedName("after")
    private Date after;
    @SerializedName("before")
    private Date before;

    public Date getAfter() {
        return after;
    }

    public void setAfter(Date after) {
        this.after = after;
    }

    public Date getBefore() {
        return before;
    }

    public void setBefore(Date before) {
        this.before = before;
    }
}
