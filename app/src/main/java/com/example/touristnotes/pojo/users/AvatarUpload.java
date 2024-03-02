package com.example.touristnotes.pojo.users;

import android.graphics.Bitmap;

public class AvatarUpload {
    private String user_id;
    private String image_avatar;
    public AvatarUpload(String user_id, String image_avatar){
        this.user_id = user_id;
        this.image_avatar = image_avatar;
    }
    public String getUser_id() {return user_id;}
    public void setUser_id() {this.user_id = user_id;}
    public String getImage_avatar() {return image_avatar;}
    public void setImage_avatar(String image_avatar) {this.image_avatar = image_avatar;}
}

