package com.example.ad.retrofittest.Common_Clases.ErrorHandler.CheckInternetConn;

import android.content.Context;

import android.support.annotation.NonNull;

import com.example.ad.retrofittest.Retrofit.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor   implements  Interceptor {

    private Context mContext;

    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!NetworkUtil.isOnline(mContext )) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }


}
