package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public class Role {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("color")
    private String color;
    @SerializedName("position")
    private int position;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
