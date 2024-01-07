package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.objects.ObjectsGrade;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterGradeObject {
    @POST("/api/grades/read_object_grade.php")
    Call<ObjectsGrade> getStringScalarGradeObject(@Body ObjectsGrade body);
}
