package com.example.ad.retrofittest.Model.Event;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class FiltrEvents implements Parcelable {
    @SerializedName("event_kind_name")
    @Expose
    private String eventKind;

    public FiltrEvents() {
    }

    public FiltrEvents setEventKind(String eventKind) {
        this.eventKind = eventKind;
        return this;
    }

    public FiltrEvents setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public FiltrEvents setDateMin(Date dateMin) {
        this.dateMin = dateMin;
        return this;
    }

    public FiltrEvents setDateMax(Date dateMax) {
        this.dateMax = dateMax;
        return this;
    }

    @SerializedName("radius")
    @Expose
    private int radius;

    @SerializedName("date_min")
    @Expose
    private Date dateMin;

    public String getEventKind() {
        return eventKind;
    }

    public int getRadius() {
        return radius;
    }

    public Date getDateMin() {
        return dateMin;
    }

    public Date getDateMax() {
        return dateMax;
    }

    @SerializedName("date_max")
    @Expose
    private Date dateMax;

    protected FiltrEvents(Parcel in) {
        eventKind = in.readString();
        radius = in.readInt();
        long tmpDateMin = in.readLong();
        dateMin = tmpDateMin != -1 ? new Date(tmpDateMin) : null;
        long tmpDateMax = in.readLong();
        dateMax = tmpDateMax != -1 ? new Date(tmpDateMax) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventKind);
        dest.writeInt(radius);
        dest.writeLong(dateMin != null ? dateMin.getTime() : -1L);
        dest.writeLong(dateMax != null ? dateMax.getTime() : -1L);
    }

    @SuppressWarnings("unused")
    public static final Creator<FiltrEvents> CREATOR = new Creator<FiltrEvents>() {
        @Override
        public FiltrEvents createFromParcel(Parcel in) {
            return new FiltrEvents(in);
        }

        @Override
        public FiltrEvents[] newArray(int size) {
            return new FiltrEvents[size];
        }
    };
}
