package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/27/16.
 */

public class MobileVersion_data {

    private String id;
    private String versionNo;
    private String message;

    public void setId(String id) {
        this.id = id;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setShow(String show) {
        this.show = show;
    }

    private String show;


    public String getId() {
        return id;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public String getMessage() {
        return message;
    }

    public String getShow() {
        return show;
    }
}
