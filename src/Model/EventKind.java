package com.example.ad.retrofittest.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventKind implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    public EventKind(int id, String name, String label) {
        this.id = id;
        this.name = name;
        this.label = label;
    }

    public EventKind(String name) {
        this.name = name;
    }

    @SerializedName("name")
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    @SerializedName("label")
    @Expose
    private String label;

    protected EventKind(Parcel in) {
        id = in.readInt();
        name = in.readString();
        label = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(label);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EventKind> CREATOR = new Parcelable.Creator<EventKind>() {
        @Override
        public EventKind createFromParcel(Parcel in) {
            return new EventKind(in);
        }

        @Override
        public EventKind[] newArray(int size) {
            return new EventKind[size];
        }
    };
}