package com.nayan.me.preventsuperbug.network.repos.interfaces.common;

import java.io.File;
import java.util.Map;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


public interface IPostOnlyRepository {
    void post(String path, Object item);

    <TResponseContent>
    void post(String path, Object item, Class<TResponseContent> clazz, Consumer<TResponseContent> success);

    <TResponseContent>
    void post(String path, Object item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error);

    <TResponseContent>
    void post(String path, Object item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete);

    void post(String path, Map<String, String> item);

    <TResponseContent>
    void post(String path, Map<String, String> item, Class<TResponseContent> clazz, Consumer<TResponseContent> success);

    <TResponseContent>
    void post(String path, Map<String, String> item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error);

    <TResponseContent>
    void post(String path, Map<String, String> item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete);


    void post(String path, Object item, Map<String, File> files);

    <TResponseContent>
    void post(String path, Object item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success);

    <TResponseContent>
    void post(String path, Object item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error);

    <TResponseContent>
    void post(String path, Object item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete);



    void post(String path, Map<String, String> item, Map<String, File> files);

    <TResponseContent>
    void post(String path, Map<String, String> item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success);

    <TResponseContent>
    void post(String path, Map<String, String> item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error);

    <TResponseContent>
    void post(String path, Map<String, String> item, Map<String, File> files, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete);
}
