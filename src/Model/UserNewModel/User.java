package com.example.ad.retrofittest.Model.UserNewModel;

import com.example.ad.retrofittest.Model.Photo.PhotoMin;
import com.example.ad.retrofittest.Model.UserFriends;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class User extends UserMin {
    @SerializedName("friends")
    @Expose
    private UserFriends friends;



    @SerializedName("gallery")
    @Expose
    private List<PhotoMin> gallery;

    public UserFriends getFriends() {
        return friends;
    }

    public void setFriends(UserFriends friends) {
        this.friends = friends;
    }

    public List <PhotoMin> getGallery() {
        return gallery;
    }

    public void setGallery(List <PhotoMin> gallery) {
        this.gallery = gallery;
    }
}
