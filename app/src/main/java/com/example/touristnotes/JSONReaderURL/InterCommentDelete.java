package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.comments.Comment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterCommentDelete {
    @POST("/api/comments/delete_comment.php")
    Call<Comment> getStringScalarCommentDelete(@Body Comment body);
}
