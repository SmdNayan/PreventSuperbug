package com.nayan.me.preventsuperbug.network.repos.implementes;

import android.util.Pair;

import com.nayan.me.preventsuperbug.network.repos.interfaces.IHttpRepository;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class HttpRepository extends BaseRepository implements IHttpRepository {
    public HttpRepository(String baseUrl) {
        super(baseUrl);
        this.headers = new HashMap<>();
        this.headers.put("User-Agent", "Mozilla/5.0");
    }

    private Map<String, String> headers;


    @Override
    public void get(String path) {
        this.get(path, null, null);
    }

    @Override
    public <TResponseContent> void get(String path, Class<TResponseContent> clazz, Consumer<TResponseContent> success) {
        this.get(path, clazz, success, null);
    }

    @Override
    public <TResponseContent> void get(String path, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error) {
        this.get(path, clazz, success, error, null);
    }

    @Override
    public <TResponseContent> void get(String path, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete) {
        super.get(path, clazz, success, error, complete, headers);
    }

    @Override
    public void post(String path, Object item) {
        this.post(path, item, null, null);
    }

    @Override
    public <TResponseContent> void post(String path, Object item, Class<TResponseContent> clazz, Consumer<TResponseContent> success) {
        this.post(path, item, clazz, success, null);
    }

    @Override
    public <TResponseContent> void post(String path, Object item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error) {
        this.post(path, item, clazz, success, error, null);
    }

    @Override
    public <TResponseContent> void post(String path, Object item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete) {
        super.post(path, item, clazz, success, error, complete, headers);
    }

    @Override
    public void post(String path, Map<String, String> item) {
        this.post(path, item, null, null);
    }

    @Override
    public <TResponseContent> void post(String path, Map<String, String> item, Class<TResponseContent> clazz, Consumer<TResponseContent> success) {
        this.post(path, item, clazz, success, null);
    }

    @Override
    public <TResponseContent> void post(String path, Map<String, String> item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error) {
        this.post(path, item, clazz, success, error, null);
    }

    @Override
    public <TResponseContent> void post(String path, Map<String, String> item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete) {
        super.post(path, item, clazz, success, error, complete, headers);
    }

    @Override
    public void post(String path, Object item, Map<String, File> files) {
        this.post(path, item, files, null,null);
    }

    @Override
    public <TResponseContent> void post(String path, Object item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success) {
        this.post(path, item, files, clazz,success, null);
    }

    @Override
    public <TResponseContent> void post(String path, Object item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error) {
        this.post(path, item, files, clazz,success, error, null);
    }

    @Override
    public <TResponseContent> void post(String path, Object item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete) {
        super.post(path, item, clazz, files, success, error, complete, headers);
    }

    @Override
    public void post(String path, Map<String, String> item, Map<String, File> files) {
        this.post(path, item, files, null, null);
    }

    @Override
    public <TResponseContent> void post(String path, Map<String, String> item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success) {
        this.post(path, item, files, clazz,success, null);
    }

    @Override
    public <TResponseContent> void post(String path, Map<String, String> item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error) {
        this.post(path, item, files, clazz,success, error, null);
    }

    @Override
    public <TResponseContent> void post(String path, Map<String, String> item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete) {
        super.post(path, item, clazz, files, success, error, complete, headers);
    }


    @Override
    public IHttpRepository putHeader(String key, String value) {
        if (headers == null)
            headers = new HashMap<>();
        headers.put(key, value);
        return this;
    }

    @Override
    public IHttpRepository putHeader(Pair<String, String> header) {
        if (headers == null)
            headers = new HashMap<>();
        headers.put(header.first, header.second);
        return this;
    }

    @Override
    public IHttpRepository putHeaders(Map<String, String> headers) {
        if (this.headers == null)
            this.headers = new HashMap<>();
        this.headers.putAll(headers);
        return this;
    }

    @Override
    public void clearHeaders() {
        if (this.headers != null)
            headers.clear();
    }
}
