package com.example.touristnotes.JSONReaderURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.example.touristnotes.pojo.users.RegistrationData;
import com.example.touristnotes.pojo.users.RegistrationResult;

//Интерфейс JSON для Регистрации
public interface InterUserRegistration {
    @POST("/api/users/registration.php")
    Call<RegistrationResult> getStringScalarRegistration(@Body RegistrationData body);
}
