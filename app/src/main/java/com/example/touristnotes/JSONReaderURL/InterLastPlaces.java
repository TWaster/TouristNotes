package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.users.LastPlaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterLastPlaces {
    @POST("/api/users/readLastPlaces.php")
    Call<LastPlaces> getStringScalarLastPlaces(@Body LastPlaces body);
}
