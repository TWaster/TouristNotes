package com.example.touristnotes.pojo.objects;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Generated("jsonshema2pojo")
public class ObjectsResult {
    public String uLogin;

    public ObjectsResult(String uLogin) {this.uLogin = uLogin;}

    @SerializedName("objects")
    @Expose
    private List<Object> objects;
    public List<Object> getObjects() {return objects;}
    public void setObjects(List<Object> objects) {this.objects = objects;}
}
