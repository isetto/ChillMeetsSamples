package com.example.ad.retrofittest.Model.Photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class PhotoFull extends PhotoMin {
    @SerializedName("is_profile_main")
    @Expose
    private Boolean isMain;

    public Boolean isMain() {
        return isMain;
    }

    public PhotoFull setMain(Boolean isMain) {
        this.isMain = isMain;
        return this;
    }
}
