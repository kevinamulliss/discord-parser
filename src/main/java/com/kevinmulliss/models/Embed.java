package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Embed {
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("timestamp")
    private Date timestamp;
    @SerializedName("description")
    private String description;
    @SerializedName("thumbnail")
    private EmbedMedia thumbnail;
    @SerializedName("video")
    private EmbedMedia video;
    @SerializedName("images")
    private List<EmbedMedia> images;
    @SerializedName("fields")
    private List<Field> fields;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EmbedMedia getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(EmbedMedia thumbnail) {
        this.thumbnail = thumbnail;
    }

    public EmbedMedia getVideo() {
        return video;
    }

    public void setVideo(EmbedMedia video) {
        this.video = video;
    }

    public List<EmbedMedia> getImages() {
        return images;
    }

    public void setImages(List<EmbedMedia> images) {
        this.images = images;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
