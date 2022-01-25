package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/25/16.
 */

public class Mobile_messages_list_data {
    // these field name must match database names
    private int id;
    private String message;
    private String m_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    public String getMessage() {
        return message;

    }

    public String getM_date() {
        return m_date;
    }
}
