package com.nayan.me.preventsuperbug.network.repos.interfaces.common;

import android.util.Pair;

import java.io.File;
import java.util.Map;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public interface IPostWithFileOnlyRepository<T> {
    void post(String url, T item, Pair<String, File> file);
    void post(String url, T item, Pair<String, File> file, Consumer<T> callback);
    void post(String url, T item, Pair<String, File> file, Consumer<T> callback, Consumer<? super Throwable> error);
    void post(String url, T item, Pair<String, File> file, Consumer<T> callback, Consumer<? super Throwable> error, Action complete);

    void post(String url, T item, Map<String, File> files);
    void post(String url, T item, Map<String, File> files, Consumer<T> callback);
    void post(String url, T item, Map<String, File> files, Consumer<T> callback, Consumer<? super Throwable> error);
    void post(String url, T item, Map<String, File> files, Consumer<T> callback, Consumer<? super Throwable> error, Action complete);
}
