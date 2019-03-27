package com.example.ad.retrofittest.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {
    public String getToken() {
        return token;
    }

    @SerializedName("token")
    @Expose
    private String token;

    public String getLogin() {
        return login;
    }

    @SerializedName("login")
    @Expose
    private String login;

    public int getUser_id() {
        return user_id;
    }

    @SerializedName("id")
    @Expose
    private int user_id;
}
