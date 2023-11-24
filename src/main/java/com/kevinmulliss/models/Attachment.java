package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public class Attachment {
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("fileName")
    private String fileName;
    @SerializedName("fileSizeBytes")
    private int fileSizeBytes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSizeBytes() {
        return fileSizeBytes;
    }

    public void setFileSizeBytes(int fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }
}
