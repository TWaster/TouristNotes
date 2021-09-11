package com.example.touristnotes.JSONReaderURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.example.touristnotes.pojo.LoginData;
import com.example.touristnotes.pojo.LoginResult;
import com.example.touristnotes.pojo.UserLogin;

public interface InterUserLogin {
    @GET("/posts/{id}")
    public Call<UserLogin> getPostWithID(@Path("id") int id);

    @GET("/api/login.php/{login}")
    public Call<UserLogin> LogUser(@Path("login") String log);

    @POST("/api/login.php")
    Call<LoginResult> getStringScalar(@Body LoginData body);

}
