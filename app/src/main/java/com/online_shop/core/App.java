package com.online_shop.core;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static App instance;
    private static String URL = "https://server-a274.herokuapp.com/";

    private UserService userService;
    private Gson gson;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        userService = retrofit.create(UserService.class);
    }

    public static App getInstance() {
        return instance;
    }

    public Gson getGson() {
        return gson;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public UserService getUserService() {
        return userService;
    }
}
