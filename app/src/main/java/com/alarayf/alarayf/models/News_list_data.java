package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/17/16.
 */

public class News_list_data {

    // these field name must match database names
    private String id;
    private String title;
    private String introtext;
    private String hits;
    private String created;

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntrotext(String introtext) {
        this.introtext = introtext;
    }

    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIntrotext() {
        return introtext;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    public News_list_data() {
    }


}
