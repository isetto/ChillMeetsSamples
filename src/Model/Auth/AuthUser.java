package com.example.ad.retrofittest.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthUser {
    @SerializedName("email")
    @Expose
    private String email;

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    @SerializedName("username")
    @Expose
    private String login;


}
