package com.example.touristnotes.JSONReaderURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.example.touristnotes.pojo.LoginData;
import com.example.touristnotes.pojo.LoginResult;
import com.example.touristnotes.pojo.UserLogin;

//Интерфейс JSON для Регистрации
public interface InterUserRegistration {
    @POST("/api/registration.php")
    Call<LoginData> getStringScalarRegistration(@Body LoginData body);
}
