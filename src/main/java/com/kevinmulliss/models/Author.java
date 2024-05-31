package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public class Author {
    @SerializedName("name")
    protected String name;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Author) {
            Author author = (Author) o;
            return this.name.equals(author.getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {

        return this.name.hashCode();
    }
}
