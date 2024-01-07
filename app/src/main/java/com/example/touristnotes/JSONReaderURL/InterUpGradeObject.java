package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.objects.ObjectsGrade;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterUpGradeObject {
    @POST("/api/grades/object_grade.php")
    Call<ObjectsGrade> getStringScalarGradeObject(@Body ObjectsGrade body);
}
