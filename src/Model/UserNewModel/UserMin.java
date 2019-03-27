package com.example.ad.retrofittest.Model.UserNewModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ad.retrofittest.Model.Photo.PhotoFull;
import com.example.ad.retrofittest.Model.Photo.PhotoMin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMin extends UserAbstract implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("login")
    @Expose
    private String login;

    public UserMin() {
    }

    @SerializedName("main_photo")
    @Expose
    private PhotoMin mainPhoto;

    @SerializedName("total_likes")
    @Expose
    private int totalLikes;

    public int getTotalLikes() {
        return totalLikes;
    }

    public String getMainPhotoUrl(){
        return this.mainPhoto.getPhotoUrl();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public PhotoMin getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(PhotoFull mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    protected UserMin(Parcel in) {
        id = in.readInt();
        login = in.readString();
        mainPhoto = (PhotoMin) in.readValue(PhotoMin.class.getClassLoader());
        totalLikes = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeValue(mainPhoto);
        dest.writeInt(totalLikes);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserMin> CREATOR = new Parcelable.Creator<UserMin>() {
        @Override
        public UserMin createFromParcel(Parcel in) {
            return new UserMin(in);
        }

        @Override
        public UserMin[] newArray(int size) {
            return new UserMin[size];
        }
    };
}
