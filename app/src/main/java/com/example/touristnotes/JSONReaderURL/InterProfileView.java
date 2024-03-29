package com.example.touristnotes.JSONReaderURL;
import com.example.touristnotes.pojo.users.ProfileView;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//Интерфейс JSON для Логина
public interface InterProfileView {
    @POST("/api/users/user_view.php")
    Call<ProfileView> getStringScalarProfileView(@Body ProfileView body);
}

