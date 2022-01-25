package com.alarayf.alarayf.models;

/**
 * Created by Mohammad on 3/15/18.
 */

public class Add_comments_data {

        private String userName;

        private String commentText;

        private String Publish;

    public String getPublish() {
        return Publish;
    }

    public void setItemID(String itemID) {

        this.itemID = itemID;
    }

    public void setPublish(String publish) {
        Publish = publish;
    }

    public void setUserID(String userID) {
        this.userID = userID;

    }

    private String itemID;

        private String userID;

    public String getItemID() {
        return itemID;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

}
