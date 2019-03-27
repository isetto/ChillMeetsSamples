package com.example.ad.retrofittest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusResponse  {
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;
}
