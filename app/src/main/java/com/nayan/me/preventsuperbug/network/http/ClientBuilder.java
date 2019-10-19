package com.nayan.me.preventsuperbug.network.http;


import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;

final class ClientBuilder {
    static OkHttpClient build(Cache cache){
        return new OkHttpClient
                .Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                //.eventListener()
                .retryOnConnectionFailure(true)
                //.addInterceptor(new GzipRequestInterceptor())
                .addInterceptor(new ClientRequestInterceptor())
                .pingInterval(10, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }
}
