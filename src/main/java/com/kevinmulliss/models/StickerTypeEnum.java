package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

public enum StickerTypeEnum {
    @SerializedName("Lottie")
    LOTTIE,
    @SerializedName("Png")
    PNG,
    @SerializedName("Apng")
    APNG
}
