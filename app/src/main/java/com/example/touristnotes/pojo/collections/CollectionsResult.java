package com.example.touristnotes.pojo.collections;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Generated("jsonshema2pojo")
public class CollectionsResult {
    public String uLogin;

    public CollectionsResult(String uLogin) {this.uLogin = uLogin;}

    @SerializedName("collections")
    @Expose
    private List<Collection> collections;

    public List<Collection> getCollections() {return collections; }

    public void setCollections(List<Collection> collections) {this.collections = collections;}
    
}
