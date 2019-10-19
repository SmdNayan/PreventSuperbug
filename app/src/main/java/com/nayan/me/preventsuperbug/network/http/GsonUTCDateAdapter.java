package com.nayan.me.preventsuperbug.network.http;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GsonUTCDateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final DateFormat dateFormat1;
    private final DateFormat dateFormat2;

    public GsonUTCDateAdapter() {
        dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);      //This is the format I need
        dateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));                               //This is the key line which converts the date to UTC which cannot be accessed with the default serializer
        dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);      //This is the format I need
        dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));                               //This is the key line which converts the date to UTC which cannot be accessed with the default serializer
    }

    @Override
    public synchronized JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateFormat1.format(date));
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            return dateFormat1.parse(jsonElement.getAsString());
        } catch (ParseException e) {
            try {
                return dateFormat2.parse(jsonElement.getAsString());
            } catch (ParseException ex) {
                throw new JsonParseException(ex);
            }
        }
    }
}