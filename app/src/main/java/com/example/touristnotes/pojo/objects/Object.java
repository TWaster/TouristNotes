package com.example.touristnotes.pojo.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Object {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    //@SerializedName("image")
    //@Expose
    //private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public String getImage() {
        //return image;
    //}

    //public void setImage(String image) {
        //this.image = image;
    //}

}
