package com.nayan.me.preventsuperbug.network.repos.interfaces.common;

import android.util.Pair;

import java.io.File;
import java.util.Map;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public interface IPutWithFileOnlyRepository<T> {
    void put(String url, T item, Pair<String, File> file);
    void put(String url, T item, Pair<String, File> file, Consumer<T> callback);
    void put(String url, T item, Pair<String, File> file, Consumer<T> callback, Consumer<? super Throwable> error);
    void put(String url, T item, Pair<String, File> file, Consumer<T> callback, Consumer<? super Throwable> error, Action complete);

    void put(String url, T item, Map<String, File> files);
    void put(String url, T item, Map<String, File> files, Consumer<T> callback);
    void put(String url, T item, Map<String, File> files, Consumer<T> callback, Consumer<? super Throwable> error);
    void put(String url, T item, Map<String, File> files, Consumer<T> callback, Consumer<? super Throwable> error, Action complete);
}
