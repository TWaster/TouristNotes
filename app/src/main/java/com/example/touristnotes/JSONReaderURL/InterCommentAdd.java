package com.example.touristnotes.JSONReaderURL;

import com.example.touristnotes.pojo.comments.Comment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterCommentAdd {
    @POST("/api/comments/add_comment.php")
    Call<Comment> getStringScalarCommentAdd(@Body Comment body);
}
