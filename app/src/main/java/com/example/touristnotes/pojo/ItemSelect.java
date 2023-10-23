package com.example.touristnotes.pojo;

public class ItemSelect {
    private String SelectItemRegion;
    private String SelectItemSubRegion;
    private int SelectItemCountry;
    private String User_name;

    public ItemSelect(String SelectItemRegion ,int SelectItemCountry, String User_name, String SelectItemSubRegion) {
        this.SelectItemRegion = SelectItemRegion;
        this.SelectItemCountry = SelectItemCountry;
        this.User_name = User_name;
        this.SelectItemSubRegion = SelectItemSubRegion;
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
