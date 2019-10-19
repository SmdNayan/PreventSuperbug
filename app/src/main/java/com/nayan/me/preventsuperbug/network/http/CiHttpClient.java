package com.nayan.me.preventsuperbug.network.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class CiHttpClient {
    private static File cacheFile;
    private static Map<String, HttpService> httpServices = new HashMap<>();
    private static RxJava2CallAdapterFactory rxJava2CallAdapterFactory;
    private static GsonConverterFactory gsonConverterFactory;
    private static OkHttpClient httpClient;
    private static Cache cache;
    private static Gson gson;
    private static Picasso picasso;

    private static OkHttpClient getHttpClient() {
        if (httpClient != null)
            return httpClient;

        return httpClient = ClientBuilder.build(getCache());
    }

    public static HttpService getHttpService(String baseUrl) {
        if (httpServices.containsKey(baseUrl))
            return httpServices.get(baseUrl);

        Retrofit retrofitClient = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(getGsonConverterFactory())
                .addCallAdapterFactory(getRxJava2CallAdapterFactory())
                .client(getHttpClient())
                .build();

        HttpService httpService = retrofitClient.create(HttpService.class);
        httpServices.put(baseUrl, httpService);
        return httpService;
    }


    public static Gson getGson() {
        if (gson != null)
            return gson;

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonUTCDateAdapter());

        return gson = gsonBuilder.create();
    }

    private static RxJava2CallAdapterFactory getRxJava2CallAdapterFactory() {
        return rxJava2CallAdapterFactory != null
                ? rxJava2CallAdapterFactory
                : (rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create());
    }

    private static GsonConverterFactory getGsonConverterFactory() {
        return gsonConverterFactory != null
                ? gsonConverterFactory
                : (gsonConverterFactory = GsonConverterFactory.create(getGson()));
    }

    private static Cache getCache() {
        return cache != null ? cache : (cache = new Cache(cacheFile, 20 * 1024 * 1024));
    }

    public static void setCacheFile(File file) {
        cacheFile = file;
    }

    static String getToken() {
        return "Bearer " + PBSBApplication.getToken();
    }


    public static void clearHttpClient() {
        try {
            if (httpClient != null)
                httpClient.dispatcher().executorService().shutdown();
            httpClient = null;
            if (httpServices != null && !httpServices.isEmpty())
                httpServices.clear();
            gson = null;
            rxJava2CallAdapterFactory = null;
            gsonConverterFactory = null;
            picasso.shutdown();
            if (cache != null && !cache.isClosed())
                cache.close();
            cache = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
