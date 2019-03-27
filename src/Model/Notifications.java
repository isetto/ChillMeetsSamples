package com.example.ad.retrofittest.Model;

import com.example.ad.retrofittest.Model.Event.EventMin;
import com.example.ad.retrofittest.Model.UserNewModel.UserMin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notifications {

    @SerializedName("code")
    @Expose
    private String code;

    public String getCode() {
        return code;
    }

    public UserMin getUserMin() {
        return userMin;
    }

    public EventMin getEventMin() {
        return eventMin;
    }

    public String getText() {
        return text;
    }

    @SerializedName("item_user_min")
    @Expose
    private UserMin userMin;

    @SerializedName("item_event_min")
    @Expose
    private EventMin eventMin;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("is_new")
    @Expose
    private Boolean isNew;

    public Boolean isNew() {
        return isNew;
    }

    public String getMarkUnread() {
        return markUnread;
    }

    @SerializedName("mark_unread_url")
    @Expose
    private String markUnread;

    public Boolean getNew() {
        return isNew;
    }

    public String getMarkRead() {
        return markRead;
    }

    @SerializedName("mark_read_url")
    @Expose
    private String markRead;

}
