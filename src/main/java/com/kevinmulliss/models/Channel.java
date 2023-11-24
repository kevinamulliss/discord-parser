package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private ChannelTypeEnum type;
    @SerializedName("categoryId")
    private String categoryId;
    @SerializedName("category")
    private String category;
    @SerializedName("name")
    private String name;
    @SerializedName("topic")
    private String topic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChannelTypeEnum getType() {
        return type;
    }

    public void setType(ChannelTypeEnum type) {
        this.type = type;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
