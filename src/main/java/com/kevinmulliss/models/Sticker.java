package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public class Sticker {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("format")
    private StickerTypeEnum type;
    @SerializedName("sourceUrl")
    private String sourceUrl;

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

    public StickerTypeEnum getType() {
        return type;
    }

    public void setType(StickerTypeEnum type) {
        this.type = type;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
