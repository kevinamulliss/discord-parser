package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Message {
    @SerializedName("timestamp")
    private Date timestamp;
    @SerializedName("content")
    private String content;
    @SerializedName("author")
    private Author author;

    public Message(Date timestamp, String content, Author author) {
        this.timestamp = timestamp;
        this.content = content;
        this.author = author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

