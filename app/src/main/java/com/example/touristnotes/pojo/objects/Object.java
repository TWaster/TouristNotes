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
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("coordinates")
    @Expose
    private String coordinates;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;
    @SerializedName("image")
    @Expose
    private String image;
    public String selectedObject;

    public Object(String selectedObject) {this.selectedObject = selectedObject;}

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
