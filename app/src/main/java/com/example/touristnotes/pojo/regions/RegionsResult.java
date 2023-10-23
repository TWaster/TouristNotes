package com.example.touristnotes.pojo.regions;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Generated("jsonschema2pojo")
public class RegionsResult {
    public String uLogin;

    public RegionsResult(String uLogin) {
        this.uLogin = uLogin;
    }

    @SerializedName("regions")
    @Expose
    private List<Region> regions;

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

}

