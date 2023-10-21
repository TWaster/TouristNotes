package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.regions.RegionsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterRegions {
    @POST("/api/readRegions.php")
    Call<RegionsResult> getStringScalarRegions(@Body RegionsResult body);
}
