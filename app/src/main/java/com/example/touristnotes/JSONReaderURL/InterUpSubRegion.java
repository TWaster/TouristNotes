package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.ItemSelect;
import com.example.touristnotes.pojo.subregions.SubRegionsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterUpSubRegion {
    @POST("api/users/up_subregion.php")
    Call<ItemSelect> getStringScalarItem(@Body ItemSelect body);
}
