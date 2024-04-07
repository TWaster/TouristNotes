package com.example.touristnotes.pojo.comments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("user_image")
    @Expose
    private String user_image;
    private String message;
    private String message1;
    private String u_owner;

    //Данные для передачи на сервер API
    public String userID;
    public String objectID;
    public String commentText;
    public String commentID;

    public Comment(String userID, String objectID, String commentText, String commentID){
        this.userID = userID;
        this.objectID = objectID;
        this.commentText = commentText;
        this.commentID = commentID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public  String getUserImage() {return user_image; }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getMessage(){return message;}
    public String getMessage1(){return message1;}

    public String getU_owner() {return u_owner; }
}