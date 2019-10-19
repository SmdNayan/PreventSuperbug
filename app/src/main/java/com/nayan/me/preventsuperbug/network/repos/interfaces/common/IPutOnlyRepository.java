package com.nayan.me.preventsuperbug.network.repos.interfaces.common;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


public interface IPutOnlyRepository<T> {
    void put(String path, T item);
    void put(String path, T item, Consumer<T> success);
    void put(String path, T item, Consumer<T> success, Consumer<? super Throwable> error);
    void put(String path, T item, Consumer<T> success, Consumer<? super Throwable> error, Action complete);
}
