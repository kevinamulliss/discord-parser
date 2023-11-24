package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public enum MessageTypeEnum {
    @SerializedName("Default")
    DEFAULT,
    @SerializedName("Reply")
    REPLY
}
