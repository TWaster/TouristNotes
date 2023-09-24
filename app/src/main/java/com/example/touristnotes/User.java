package com.example.touristnotes;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String avatar;
    private Integer country;
    private Integer region;
    private Integer sub_region;
    private Integer runk;
    private Integer level;
    private String unique_key;

    public User(String name, String avatar, Integer country, Integer region, Integer sub_region, Integer runk, Integer level, String unique_key) {
        this.name = name;
        this.avatar = avatar;
        this.country = country;
        this.region = region;
        this.sub_region = sub_region;
        this.runk = runk;
        this.level = level;
        this.unique_key = unique_key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public void setSub_region(Integer sub_region) {
        this.sub_region = sub_region;
    }

    public void setRunk(Integer runk) {
        this.runk = runk;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setUnique_key(String unique_key) {
        this.unique_key = unique_key;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getCountry() {
        return country;
    }

    public Integer getRegion() {
        return region;
    }

    public Integer getSub_region() {
        return sub_region;
    }

    public Integer getRunk() {
        return runk;
    }

    public Integer getLevel() {
        return level;
    }

    public String getUnique_key() {
        return unique_key;
    }
}
