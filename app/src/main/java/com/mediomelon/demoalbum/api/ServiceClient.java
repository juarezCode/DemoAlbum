package com.mediomelon.demoalbum.api;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {

    private ServiceClient() {
        //constructor vacio
    }

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static IAlbumService createAlbumService() {
        return getRetrofitClient().create(IAlbumService.class);
    }

    public static IPhotoService createPhotoService() {
        return getRetrofitClient().create(IPhotoService.class);
    }

    public static IUserService createUserService() {
        return getRetrofitClient().create(IUserService.class);
    }

    public static Retrofit getRetrofitClient() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttp())
                .build();
    }

    private static OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }

    private static Interceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }
}
