package com.example.ad.retrofittest.Retrofit;

import android.content.Context;
import android.util.Log;

import com.example.ad.retrofittest.Retrofit.Services.APIService;
import com.example.ad.retrofittest.Retrofit.Services.LoginService;
import com.example.ad.retrofittest.Retrofit.Services.NewServices;
import com.example.ad.retrofittest.Retrofit.Services.TestServices;

import junit.framework.Test;

/**
 * Created by ad on 2018-01-27.
 */

public class ApiUtils {



    private static final String BASE_URL = "https://adrian.kubahaha.tk/";
    private static final String LOGIN_URL = "https://auth.chillmeets.pl/";
    private static final String GENERAL_URL = "https://api.chillmeets.pl/";
    private static final String TEST_URL = "https://api.chillmeets.pl/test/";



    private ApiUtils() {
    }



    public static APIService getAPIService(Context context) {

        return RetrofitClient.getClient(BASE_URL, context).create(APIService.class);

    }

    public static LoginService getLoginService(Context context) {

        return RetrofitClient.getClient(LOGIN_URL, context).create(LoginService.class);

    }

    public static NewServices getGeneralService(Context context) {

        return RetrofitClient.getClient(GENERAL_URL, context).create(NewServices.class);

    }

    public static TestServices getTestService(Context context) {
        TestServices test = RetrofitClient.getClient(TEST_URL, context).create(TestServices.class);
        Log.i("HWDP",  "crated some shit yo!");
        return test;

    }
}