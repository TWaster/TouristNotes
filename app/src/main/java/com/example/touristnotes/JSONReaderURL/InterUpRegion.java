package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.ItemSelect;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterUpRegion {
    @POST("/api/users/up_region.php")
    Call<ItemSelect> getStringScalarItem(@Body ItemSelect body);
}
