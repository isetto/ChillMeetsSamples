package com.example.ad.retrofittest.Model;

import com.example.ad.retrofittest.Model.UserNewModel.UserMin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserFriends {
    @SerializedName("others_requests")
    @Expose
    private List<UserMin> othersRequests;


    public List <UserMin> getOthersRequests() {
        return othersRequests;
    }

    public List <UserMin> getMyRequests() {
        return myRequests;
    }

    public List <UserMin> getAccepted() {
        return accepted;
    }

    @SerializedName("my_requests")
    @Expose
    private List<UserMin> myRequests;

    @SerializedName("accepted")
    @Expose
    private List<UserMin> accepted;
}
