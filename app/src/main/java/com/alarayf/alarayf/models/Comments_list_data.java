package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 11/17/16.
 */

public class Comments_list_data {

    // these field name must match database names
    private String id;
    private String userName;
    private String commentText;

    private String commentDate;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getCommentDate() {
        return commentDate;
    }
}
