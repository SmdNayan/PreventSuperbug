package com.nayan.me.preventsuperbug.network.repos.interfaces.common;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public interface IGetOnlyRepository {

    void get(String path);

    <TResponseContent> void get(String path, Class<TResponseContent> clazz, Consumer<TResponseContent> success);

    <TResponseContent> void get(String path, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error);

    <TResponseContent> void get(String path, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete);
}
