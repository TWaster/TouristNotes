package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.objects.MarkedObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterMarkedObject {
    @POST("/api/users/marked_object.php")
    Call<MarkedObject> getStringScalarMarkedObject(@Body MarkedObject body);
}
