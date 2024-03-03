package com.example.touristnotes.pojo.users;

import java.util.List;

public class LastPlaces {
    public String uLogin;
    public LastPlaces(String uLogin) {
        this.uLogin = uLogin;
    }

    private List<LastPlace> lastPlaces;
    public List<LastPlace> getLastPlaces() {
        return lastPlaces;
    }
    public void setLastPlaces(List<LastPlace> lastPlaces) {
        this.lastPlaces = lastPlaces;
    }

}