package com.example.touristnotes.JSONReaderURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.example.touristnotes.pojo.RegistrationData;
import com.example.touristnotes.pojo.RegistrationResult;

//Интерфейс JSON для Регистрации
public interface InterUserRegistration {
    @POST("/api/registration.php")
    Call<RegistrationData> getStringScalarRegistration(@Body RegistrationData body);
}
