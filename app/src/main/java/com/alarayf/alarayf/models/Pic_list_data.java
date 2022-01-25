package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/25/16.
 */

public class Pic_list_data {
    // these field name must match database names
    private int id;
    private String title;
    private String introtext;

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntrotext(String introtext) {
        this.introtext = introtext;
    }

    private String imageName;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIntrotext() {
        return introtext;
    }

    public String getImageName() {
        return imageName;
    }
    public Pic_list_data() {
    }
}
