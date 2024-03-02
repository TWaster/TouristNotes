package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.users.AvatarUpload;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterUpUserAvatar {
    @POST("/api/users/avatar_update.php")
    Call<AvatarUpload> getStringScalarUserImageAdd (@Body AvatarUpload body);
}
