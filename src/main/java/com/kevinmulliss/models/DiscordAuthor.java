package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscordAuthor extends Author {
    @SerializedName("id")
    private String id;
    @SerializedName("discriminator")
    private String discriminator;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("color")
    private String color;
    @SerializedName("isBot")
    private boolean isBot;
    @SerializedName("roles")
    private List<Role> roles;
    @SerializedName("avatarUrl")
    private String avatarUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DiscordAuthor) {
            DiscordAuthor author = (DiscordAuthor) o;
            return author.getName().equals(name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}
