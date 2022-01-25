package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/8/16.
 */

public class Event_list_data {

    private String id;
    private String title;
    private String Remain;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRemain(String remain) {
        Remain = remain;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String startdate;
    private String desc;



    public Event_list_data() {

    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRemain() {
        return Remain;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getDesc() {
        return desc;
    }





}
