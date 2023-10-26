package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.objects.ObjectsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterObjects {
    @POST("/api/objects/readObjects.php")
    Call<ObjectsResult> getStringScalarObjects(@Body ObjectsResult body);
}
