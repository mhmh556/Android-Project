package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 3/15/18.
 */

public class Token_data {

    public Token_data(String token, String device) {
        this.token = token;
        this.device = device;
    }

    public String getToken() {
        return token;
    }

    public String getDevice() {
        return device;
    }

    private String token;

        private String device;

    public void setToken(String token) {
        this.token = token;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
