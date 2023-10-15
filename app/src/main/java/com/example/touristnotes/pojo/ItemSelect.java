package com.example.touristnotes.pojo;

public class ItemSelect {
    private int SelectItemCountry;
    private String User_name;

    public ItemSelect(int SelectItemCountry, String User_name) {
        this.SelectItemCountry = SelectItemCountry;
        this.User_name = User_name;
    }

    public int getCountry() {
        return SelectItemCountry;
    }
    public void getCountry(int SelectItemCountry) {this.SelectItemCountry = SelectItemCountry;}
    public String getUser_name() {
        return User_name;
    }
    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }
}