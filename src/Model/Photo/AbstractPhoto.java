package com.example.ad.retrofittest.Model.Photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class AbstractPhoto {


    @SerializedName("image_url")
    @Expose
    private String photoUrl;

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public AbstractPhoto setDescription(String description) {
        this.description = description;
        return this;
    }

    public AbstractPhoto setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }
}
