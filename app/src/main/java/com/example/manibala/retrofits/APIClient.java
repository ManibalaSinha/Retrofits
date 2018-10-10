package com.example.manibala.retrofits;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    //getClient() method will be called every time while setting up Retrofit interface
    static Retrofit getClient(){
        //interceptor can monitor, rewrite, & retry calls
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //calling addInterceptor() on OkHttpClient.Builder() to register application interceptor
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit=new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
