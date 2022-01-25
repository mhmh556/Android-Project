package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/25/16.
 */

public class Vid_list_data {

    // these field name must match database names
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntrotext(String introtext) {
        this.introtext = introtext;
    }

    public void setMyVIdeo(String myVIdeo) {
        this.myVIdeo = myVIdeo;
    }

    private String title;
    private String introtext;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIntrotext() {
        return introtext;
    }

    public String getMyVIdeo() {
        return myVIdeo;
    }

    private String myVIdeo;

}
