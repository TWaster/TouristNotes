package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.collections.CollectionsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterCollections {
    @POST("/api/readCollections.php")
    Call<CollectionsResult> getStringScalarCollections(@Body CollectionsResult body);
}
