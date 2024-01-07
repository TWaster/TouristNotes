package com.example.touristnotes.pojo.objects;

public class ObjectsGrade {
    public String grade;
    public String objectID;
    public String userID;

    public ObjectsGrade(String objectID, String userID, String grade){
        this.objectID = objectID;
        this.userID = userID;
        this.grade = grade;
    }
}
