package com.example.touristnotes.pojo;

public class ItemSelect {
    private Integer SelectItemCountry;
    private String Unique_key;

    public ItemSelect(int SelectItemCountry, String Unique_key) {
        this.SelectItemCountry = SelectItemCountry;
        this.Unique_key = Unique_key;
    }

    public Integer getCountry() {
        return SelectItemCountry;
    }
    public void getCountry(Integer SelectItemCountry) {this.SelectItemCountry = SelectItemCountry;}
    public String getUnique_key() {
        return Unique_key;
    }
    public void setUnique_key(String Unique_key) {
        this.Unique_key = Unique_key;
    }
}