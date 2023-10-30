package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.objects.Object;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterSelectedObject {
    @POST("/api/objects/readObjectInfo.php")
    Call<Object> getStringScalarObjectInfo(@Body Object body);
}
