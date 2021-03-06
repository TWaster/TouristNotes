package com.example.touristnotes.pojo;

public class LoginResult {
    private long id;
    private String nickname;
    private String avatar;
    private String rank;
    private int level;
    private int s_contry;
    private int s_region;
    private int s_sub_region;
    private String unique_key;

    public LoginResult(long id, String avatar, String nickname, String rank, int level, int s_contry, int s_region, int s_sub_region, String unique_key) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.rank = rank;
        this.level = level;
        this.s_contry = s_contry;
        this.s_region = s_region;
        this.s_sub_region = s_sub_region;
        this.unique_key = unique_key;
    }

    //GET-методы для вывода в форму
    public long getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public int getS_contry() {
        return s_contry;
    }

    public int getS_region() {
        return s_region;
    }

    public int getS_sub_region() {
        return s_sub_region;
    }

    public String getUnique_key() {
        return unique_key;
    }

    //SET-методы для внесение изменений
    public void setId(long id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setS_contry(int s_contry) {
        this.s_contry = s_contry;
    }

    public void setS_region(int s_region) {
        this.s_region = s_region;
    }

    public void setS_sub_region(int s_sub_region) {
        this.s_sub_region = s_sub_region;
    }

    public void setUnique_key(String unique_key) {
        this.unique_key = unique_key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginResult that = (LoginResult) o;

        if (id != that.id) return false;
        if (level != that.level) return false;
        if (s_contry != that.s_contry) return false;
        if (s_region != that.s_region) return false;
        if (s_sub_region != that.s_sub_region) return false;
        if (!nickname.equals(that.nickname)) return false;
        if (!avatar.equals(that.avatar)) return false;
        if (!rank.equals(that.rank)) return false;
        return unique_key.equals(that.unique_key);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + nickname.hashCode();
        result = 31 * result + avatar.hashCode();
        result = 31 * result + rank.hashCode();
        result = 31 * result + level;
        result = 31 * result + s_contry;
        result = 31 * result + s_region;
        result = 31 * result + s_sub_region;
        result = 31 * result + unique_key.hashCode();
        return result;
    }
}


