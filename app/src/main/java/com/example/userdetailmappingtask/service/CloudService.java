package com.example.userdetailmappingtask.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CloudService {
  private static Retrofit retrofit = null;
  private static final String BASE_URL = "http://13.127.95.246:7000/marketing/";

  private CloudService() {
  }

  public static synchronized Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();

    }
    return retrofit;
  }
}
