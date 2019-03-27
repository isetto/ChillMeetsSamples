package com.example.ad.retrofittest.Model.Photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoEdit extends AbstractPhoto {


    @SerializedName("is_profile_main")
    @Expose
    private Boolean isMain;

    public PhotoEdit(String description, Boolean isMain) {
        this.setDescription(description);
        this.isMain = isMain;
    }

    public PhotoEdit() {
    }

    public Boolean isMain() {
        return isMain;
    }

    public PhotoEdit setMain(Boolean isMain) {
        this.isMain = isMain;
        return this;
    }
}
