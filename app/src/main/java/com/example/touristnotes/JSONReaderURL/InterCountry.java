package com.example.touristnotes.JSONReaderURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.example.touristnotes.pojo.ItemSelect;

public interface InterCountry {
    @POST("/api/users/up_country.php")
    Call<ItemSelect> getStringScalarItem(@Body ItemSelect body);
}
