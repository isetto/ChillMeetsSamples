package com.example.ad.retrofittest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    public String getImage_url() {
        return image_url;
    }

    @SerializedName("image_url")
    @Expose
    private String image_url;

    @SerializedName("description")
    @Expose
    private String description_of_image;
}
