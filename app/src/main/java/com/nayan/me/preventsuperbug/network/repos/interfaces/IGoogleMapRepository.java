package com.nayan.me.preventsuperbug.network.repos.interfaces;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by arifk on 14.10.17.
 */

public interface IGoogleMapRepository {
    void getPlacesSuggestion(String apiPath, Consumer<List<Object>> success, Consumer<? super Throwable> error);
}
