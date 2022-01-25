package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/27/16.
 */

public class Tree_data {

    private String id;
    private String Name;

    public String getAlive() {
        return alive;
    }

    private String alive;

    public void setId(String id) {
        this.id = id;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }

    public Tree_data() {
    }

    public void setName(String name) {
        Name = name;
    }

    public void setParent_id(String parent_id) {
        Parent_id = parent_id;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getParent_id() {
        return Parent_id;
    }

    public String getDate() {
        return Date;
    }



    private String Parent_id;
    private String Date;
}
