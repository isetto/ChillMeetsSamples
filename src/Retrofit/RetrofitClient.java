package com.example.ad.retrofittest.Retrofit;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.ad.retrofittest.Account.Login;
import com.example.ad.retrofittest.Common_Clases.ErrorHandler.CheckInternetConn.ConnectivityInterceptor;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ad on 2018-01-16.
 */

public class RetrofitClient {

	private static Retrofit retrofit = null;




	public static Retrofit getClient(String baseUrl, Context context) {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

		Dispatcher dispatcher=new Dispatcher();
		httpClient.dispatcher(dispatcher);


		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(chain -> {
					Request newRequest = chain.request().newBuilder()
							.addHeader("Authorization", "Bearer " + Login.Companion.getTokenGlobal())
							.build();
					return chain.proceed(newRequest);
				})
				.addInterceptor(chain -> {
						Request request = chain.request();
						Response response = chain.proceed(request);

					if (response.code() == 500)
						Toast.makeText(context, "Błąd serwera, 500", Toast.LENGTH_LONG).show();

					if(response.code() == 404)
						Toast.makeText(context, "Nie znaleziono, 404", Toast.LENGTH_LONG).show();

					return response;
				})
				.addInterceptor(interceptor)
				.addInterceptor(new ConnectivityInterceptor(context))
				.build();

		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.client(client)
					.baseUrl(baseUrl)
					.addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
							.setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
							.create()
					))
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build();
		}
		return retrofit;
	}
}

