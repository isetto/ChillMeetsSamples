package com.example.ad.retrofittest.Model;

import com.example.ad.retrofittest.Model.UserNewModel.User;
import com.example.ad.retrofittest.Model.UserNewModel.UserMin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Activity {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String activity_name;

    @SerializedName("description")
    @Expose
    private String description_of_activity;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("start_date")
    @Expose
    private Date start_date;

    @SerializedName("end_date")
    @Expose
    private Date end_date;

    @SerializedName("event_kind")
    @Expose(deserialize = false, serialize = false)
    private EventKind kind_of_activity;

    @SerializedName("event_kind_name")
    @Expose
    private String kind_of_activity_string;


    @SerializedName("lat")
    @Expose
    private Double lat;

    @SerializedName("lng")
    @Expose
    private Double lng;

    @SerializedName("guests")
    @Expose
    private EventGuests guests;

    @SerializedName("mods")
    @Expose
    private List <UserMin> mods;

//    @SerializedName("creator_id")
//    @Expose
//    private int adminId;

    @SerializedName("creator")
    @Expose
    private User admin;

    @SerializedName("main_photo")
    @Expose
    private String mainPhotoUrl;

    @SerializedName("event_type")
    @Expose
    private String eventAccess;

    public User getCreator() {
        return this.admin;
    }

    public String getActivity_address() {
        return address;
    }

    public Double getLat() {
        return lat;
    }

    private  Activity(Double lat, Double lng, String activity_address, String activity_name, String kind_of_activity, String description_of_activity, String start_date, int activity_id) {
        SimpleDateFormat polishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        this.lat = lat;
        this.address = activity_address;
//        this.adminId = creator;
        this.lng = lng;
        this.activity_name = activity_name;
        this.kind_of_activity = new EventKind(kind_of_activity);
        this.description_of_activity = description_of_activity;
        this.kind_of_activity_string = this.kind_of_activity.getName();
        try {
            this.start_date = polishDate.parse(start_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        this.mainPhotoUrl = photo_of_activity;
    }

    public Double getLng() {
        return lng;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public EventKind getEventKind() {
        return kind_of_activity;
    }

    public String getKind_of_activity() {
        return kind_of_activity.getName();
    }


    public String getDescription_of_activity() {
        return description_of_activity;
    }

    public String getStartDateOnly() {
        return start_date.toString().substring(0, 9);
    }

    public String getStartHourOnly() {
        return start_date.toString().substring(11, 16);
    }

    public String getPhoto_of_activity() {
        return mainPhotoUrl;
    }

    public int getActivity_id() {
        return id;
    }


}
