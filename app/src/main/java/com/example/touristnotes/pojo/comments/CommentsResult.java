package com.example.touristnotes.pojo.comments;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsResult {
    //Параметры для передачи в API
    public String userID;
    public String objectID;
    public String commentID;
    public CommentsResult(String userID, String objectID, String commentID) {
        this.userID = userID;
        this.objectID = objectID;
        this.commentID = commentID;
    }


    @SerializedName("comments")
    @Expose
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
