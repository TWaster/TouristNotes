package com.example.touristnotes.pojo;

public class User {
    private long id;
    private String avatar;
    private String nickname;
    private String f_name;
    private String s_name;
    private String rank;
    private int level;
    private int num_achievements;
    private String unique_key;

    public User(long id, String avatar, String nickname, String f_name, String s_name, String rank, int level, int num_achievements, String unique_key) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.f_name = f_name;
        this.s_name = s_name;
        this.rank = rank;
        this.level = level;
        this.num_achievements = num_achievements;
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

    public String getF_name() {
        return f_name;
    }

    public String getS_name() {
        return s_name;
    }

    public String getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public int getNum_achievements() {
        return num_achievements;
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

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setNum_achievements(int num_achievements) {
        this.num_achievements = num_achievements;
    }

    public void setUnique_key(String unique_key) {
        this.unique_key = unique_key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (level != user.level) return false;
        if (num_achievements != user.num_achievements) return false;
        if (avatar != null ? !avatar.equals(user.avatar) : user.avatar != null) return false;
        if (!nickname.equals(user.nickname)) return false;
        if (!f_name.equals(user.f_name)) return false;
        if (!s_name.equals(user.s_name)) return false;
        if (!rank.equals(user.rank)) return false;
        return unique_key.equals(user.unique_key);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + nickname.hashCode();
        result = 31 * result + f_name.hashCode();
        result = 31 * result + s_name.hashCode();
        result = 31 * result + rank.hashCode();
        result = 31 * result + level;
        result = 31 * result + num_achievements;
        result = 31 * result + unique_key.hashCode();
        return result;
    }
}


