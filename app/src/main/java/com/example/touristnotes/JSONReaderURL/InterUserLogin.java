package com.example.touristnotes.JSONReaderURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.example.touristnotes.pojo.users.LoginData;
import com.example.touristnotes.pojo.users.LoginResult;

//Интерфейс JSON для Логина
public interface InterUserLogin {
    @POST("/api/users/login.php")
    Call<LoginResult> getStringScalarLogin(@Body LoginData body);
}

