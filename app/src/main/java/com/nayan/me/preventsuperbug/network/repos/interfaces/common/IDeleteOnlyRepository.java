package com.nayan.me.preventsuperbug.network.repos.interfaces.common;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public interface IDeleteOnlyRepository {
    void delete(String url);
    void delete(String url, Consumer<String> callback);
    void delete(String url, Consumer<String> callback, Consumer<? super Throwable> error);
    void delete(String url, Consumer<String> callback, Consumer<? super Throwable> error, Action complete);
}
