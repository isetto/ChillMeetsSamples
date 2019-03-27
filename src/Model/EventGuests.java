package com.example.ad.retrofittest.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ad.retrofittest.Model.UserNewModel.UserMin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventGuests  {
    @SerializedName("participants")
    @Expose
    private List<EventUserMin> participants;

    @SerializedName("invited")
    @Expose
    private List<EventUserMin> invited;

    public EventGuests(List <EventUserMin> participants, List <EventUserMin> invited) {
        this.participants = participants;
        this.invited = invited;
    }

    public List <EventUserMin> getParticipants() {
        return participants;
    }

    public List <EventUserMin> getInvited() {
        return invited;
    }


}