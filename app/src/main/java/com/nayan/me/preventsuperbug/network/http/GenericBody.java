package com.nayan.me.preventsuperbug.network.http;


import com.google.gson.reflect.TypeToken;

public class GenericBody<T> {
    final T body;
    final TypeToken<T> typeToken;

    public GenericBody(final T body, final TypeToken<T> typeToken) {
        this.body = body;
        this.typeToken = typeToken;
    }
}
