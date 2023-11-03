package com.example.touristnotes.pojo.objects;

public class MarkedObject {
    private String message;
    public String objectID;
    public String userID;

    public MarkedObject (String objectID, String userID){
        this.objectID = objectID;
        this.userID = userID;
    }

    public String getMessage(){return message;}
    public void setMessage(){this.message = message;}
}
