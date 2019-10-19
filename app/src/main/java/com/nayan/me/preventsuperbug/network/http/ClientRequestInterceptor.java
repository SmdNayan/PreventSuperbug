package com.nayan.me.preventsuperbug.network.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ClientRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request newRequest = originalRequest.newBuilder()
                .header("Authorization", CiHttpClient.getToken())
                .method(originalRequest.method(), originalRequest.body())
                .build();
        return chain.proceed(newRequest);
    }
}
