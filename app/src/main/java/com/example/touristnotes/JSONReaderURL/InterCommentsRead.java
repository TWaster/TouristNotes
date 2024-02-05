package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.comments.CommentsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterCommentsRead {
    @POST("/api/comments/read_comment.php")
    Call<CommentsResult> getStringScalarComments(@Body CommentsResult body);
}
