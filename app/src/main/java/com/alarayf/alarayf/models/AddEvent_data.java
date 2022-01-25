package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/27/16.
 */

public class AddEvent_data {

    private String title;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    private String desc;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    private String startdate;
    private String shortdesc;
}
