package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public class Server {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("iconUrl")
    private String iconUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
