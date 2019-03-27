package com.example.ad.retrofittest.Model.Photo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoMin extends AbstractPhoto implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("owner_id")
    @Expose
    private int ownerId;

    public int getOwnerId() {
        return ownerId;
    }

    public PhotoMin() {
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public PhotoMin setId(int id) {
        this.id = id;
        return this;
    }

    protected PhotoMin(Parcel in) {
        id = in.readInt();
        ownerId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(ownerId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PhotoMin> CREATOR = new Parcelable.Creator<PhotoMin>() {
        @Override
        public PhotoMin createFromParcel(Parcel in) {
            return new PhotoMin(in);
        }

        @Override
        public PhotoMin[] newArray(int size) {
            return new PhotoMin[size];
        }
    };
}