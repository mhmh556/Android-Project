package com.alarayf.alarayf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammad on 4/12/18.
 */

public class EmailNotification_Response {

    @SerializedName("HttpResponse")
    @Expose
    private String HttpResponse;

    public String getHttpResponse() {
        return HttpResponse;
    }



    public void setHttpResponse(String httpResponse) {
        HttpResponse = httpResponse;
    }



}
