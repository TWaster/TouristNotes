package com.example.touristnotes.pojo.objects;

public class MarkedObject {

    private String message;
    private String message1;
    public String objectID;
    public String userID;

    public MarkedObject (String objectID, String userID){
        this.objectID = objectID;
        this.userID = userID;
    }

    public String getMessage(){return message;}
    public String getMessage1(){return message1;}
    public void setMessage(){this.message = message;}
    public void setMessag1e(){this.message1 = message1;}
}
