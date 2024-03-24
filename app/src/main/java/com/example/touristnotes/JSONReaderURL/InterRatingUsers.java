package com.example.touristnotes.JSONReaderURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.example.touristnotes.pojo.rating.RatingUsers;

public interface InterRatingUsers {
    @POST("/api/rating/rating_users.php")
    Call<RatingUsers> getStringScalarRatingUsers(@Body RatingUsers body);
}
