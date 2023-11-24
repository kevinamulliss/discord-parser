package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public class Field {
    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private String value;
    @SerializedName("isInline")
    private boolean isInline;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isInline() {
        return isInline;
    }

    public void setInline(boolean inline) {
        isInline = inline;
    }
}
