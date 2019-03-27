package com.example.ad.retrofittest.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ad.retrofittest.Model.UserNewModel.UserMin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventUserMin extends UserMin  {

    public int isRated() {
        return isRated;
    }

    @SerializedName("isRated")
    @Expose
    private int isRated;

    public int getActivity_id() {
        return activity_id;
    }

    @SerializedName("activity_id")
    @Expose
    private int activity_id;



    protected EventUserMin(Parcel in) {
        super(in);
        isRated = in.readInt();
        activity_id = in.readInt();
    }


}