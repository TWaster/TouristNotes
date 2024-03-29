package com.example.touristnotes.pojo.users;

public class ProfileView {

    private String id;
    private String nickname;
    private String avatar;
    private String rank;
    private String level;
    private Integer currentExp;
    private Integer needExp;

    private final String login;
    public ProfileView(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(Integer currentExp) {
        this.currentExp = currentExp;
    }

    public Integer getNeedExp() {
        return needExp;
    }

    public void setNeedExp(Integer needExp) {
        this.needExp = needExp;
    }

}