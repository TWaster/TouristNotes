package com.example.touristnotes.pojo.subregions;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Generated("jsonschema2pojo")
public class SubRegionsResult {
    public String uLogin;

    public SubRegionsResult(String uLogin) {
        this.uLogin = uLogin;
    }

    @SerializedName("subregions")
    @Expose
    private List<SubRegion> subregions;

    public List<SubRegion> getSubRegions() {
        return subregions;
    }

    public void setSubRegions(List<SubRegion> subregions) {
        this.subregions = subregions;
    }

}

