package com.example.touristnotes.pojo;

import java.util.ArrayList;

public class RegionsResult {
    private String uLogin;
    public RegionsResult(String uLogin) {
        this.uLogin = uLogin;
    }

    public static class Region{
        public String id;
        public String name;
        public String image;
    }

    public static class Root{
        public ArrayList<Region> regions;
        public int success;
    }
}
