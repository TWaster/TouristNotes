package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.subregions.SubRegionsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterSubRegions {
    @POST("/api/readSubRegions.php")
    Call<SubRegionsResult> getStringScalarSubRegions(@Body SubRegionsResult body);
}
