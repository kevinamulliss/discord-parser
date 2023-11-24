package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reaction {
    @SerializedName("emoji")
    private Emoji emoji;
    @SerializedName("count")
    private int count;
    @SerializedName("users")
    private List<User> users;

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
