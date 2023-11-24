package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public enum ChannelTypeEnum {
    @SerializedName("DirectTextChat")
    DIRECT_MESSAGE,
    @SerializedName("GuildTextChat")
    SERVER
}
