package com.example.touristnotes.pojo.users;

public class LoginResult {
    private String id;
    private String nickname;
    private String avatar;
    private Integer rank;
    private String level;
    private Integer currentExp;
    private Integer needExp;
    private String s_country;
    private String s_region;
    private String s_sub_region;
    private String unique_key;
    private String message;

    public LoginResult(String id, String avatar, String nickname, Integer rank, String level, Integer currentExp, Integer needExp, String s_country, String s_region, String s_sub_region, String unique_key) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.rank = rank;
        this.level = level;
        this.currentExp = currentExp;
        this.needExp = needExp;
        this.s_country = s_country;
        this.s_region = s_region;
        this.s_sub_region = s_sub_region;
        this.unique_key = unique_key;
        this.message = message;
    }

    //GET-методы для вывода в форму
    public String getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getRank() {
        return rank;
    }

    public String getLevel() {
        return level;
    }
    public Integer getCurrentExp(){return currentExp;}
    public Integer getNeedExp(){return needExp;}

    public String getS_country() {
        return s_country;
    }

    public String getS_region() {
        return s_region;
    }

    public String getS_sub_region() {
        return s_sub_region;
    }

    public String getUnique_key() {
        return unique_key;
    }

    public String getMessage() {
        return message;
    }

    //SET-методы для внесение изменений
    public void setId(String id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setS_country(String s_country) {
        this.s_country = s_country;
    }

    public void setS_region(String s_region) {
        this.s_region = s_region;
    }

    public void setS_sub_region(String s_sub_region) {
        this.s_sub_region = s_sub_region;
    }

    public void setUnique_key(String unique_key) {
        this.unique_key = unique_key;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginResult that = (LoginResult) o;

        if (id != that.id) return false;
        if (!level.equals(that.level)) return false;
        if (!s_country.equals(that.s_country)) return false;
        if (!s_region.equals(that.s_region)) return false;
        if (!s_sub_region.equals(that.s_sub_region)) return false;
        if (!nickname.equals(that.nickname)) return false;
        if (!avatar.equals(that.avatar)) return false;
        if (!rank.equals(that.rank)) return false;
        return unique_key.equals(that.unique_key);
    }
}


