package com.example.ad.retrofittest.Common_Clases.ErrorHandler.CheckInternetConn;


import java.io.IOException;

public class NoConnectivityException extends IOException {



    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}