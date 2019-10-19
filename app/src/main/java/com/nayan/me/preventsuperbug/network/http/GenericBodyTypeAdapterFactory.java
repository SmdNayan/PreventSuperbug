package com.nayan.me.preventsuperbug.network.http;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GenericBodyTypeAdapterFactory implements TypeAdapterFactory {
    private static final TypeAdapterFactory genericBodyTypeAdapterFactory = new GenericBodyTypeAdapterFactory();

    private GenericBodyTypeAdapterFactory() {
        }

    static TypeAdapterFactory getGenericBodyTypeAdapterFactory() {
        return genericBodyTypeAdapterFactory;
    }

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        if ( !GenericBody.class.isAssignableFrom(typeToken.getRawType()) ) {
            return null;
        }
        final TypeAdapter<GenericBody<T>> genericBodyTypeAdapter = new TypeAdapter<GenericBody<T>>() {
            @Override
            public void write(final JsonWriter out, final GenericBody<T> value)
                    throws IOException {
                final T body = value.body;
                final TypeAdapter<T> typeAdapter = gson.getDelegateAdapter(GenericBodyTypeAdapterFactory.this, value.typeToken);
                typeAdapter.write(out, body);
            }

            @Override
            public GenericBody<T> read(final JsonReader in) {
                throw new UnsupportedOperationException();
            }
        };
        @SuppressWarnings("unchecked")
        final TypeAdapter<T> typeAdapter = (TypeAdapter<T>) genericBodyTypeAdapter;
        return typeAdapter;
    }
}
