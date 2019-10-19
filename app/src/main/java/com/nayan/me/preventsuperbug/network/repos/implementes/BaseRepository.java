package com.nayan.me.preventsuperbug.network.repos.implementes;

import android.content.ContentResolver;

import androidx.annotation.NonNull;

import com.nayan.me.preventsuperbug.core.PBSBApplication;
import com.nayan.me.preventsuperbug.network.HttpNoContentResponse;
import com.nayan.me.preventsuperbug.network.http.CiHttpClient;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

abstract class BaseRepository {
    private String baseUrl;
    private static Consumer<ResponseBody> defaultSuccess = new Consumer<ResponseBody>() {
        @Override
        public void accept(ResponseBody data) throws Exception {
            System.out.printf("Data Fetched");
        }
    };
    private static Consumer<? super Throwable> defaultError = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            throwable.printStackTrace();
        }
    };
    private static Action defaultCompleted = new Action() {
        @Override
        public void run() throws Exception {
            System.out.printf("Observable Completed");
        }
    };

    BaseRepository(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    <T>
    void get(String apiPath, Class<T> clazz, Consumer<T> success, Consumer<? super Throwable> error, Action complete, Map<String, String> headers) {

        Observable<ResponseBody> get = headers == null || headers.isEmpty()
                ? CiHttpClient.getHttpService(baseUrl).get(apiPath)
                : CiHttpClient.getHttpService(baseUrl).get(apiPath, headers);

        bindSubscriber(get, clazz, success, error, complete);
    }

    <TResponseContent>
    void post(String apiPath, Object body, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete, Map<String, String> headers) {

        boolean isHeadersEmpty = headers == null || headers.isEmpty();

        Observable<ResponseBody> post = isHeadersEmpty
                ? CiHttpClient.getHttpService(baseUrl).post(apiPath, body)
                : CiHttpClient.getHttpService(baseUrl).post(apiPath, body, headers);

        bindSubscriber(post, clazz, success, error, complete);
    }

    <TResponseContent>
    void post(String apiPath, Map<String, String> body, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete, Map<String, String> headers) {

        Observable<ResponseBody> post = headers == null || headers.isEmpty()
                ? CiHttpClient.getHttpService(baseUrl).post(apiPath, body)
                : CiHttpClient.getHttpService(baseUrl).post(apiPath, body, headers);

        bindSubscriber(post, clazz, success, error, complete);
    }

    <TResponseContent>
    void post(String apiPath, Object item, Class<TResponseContent> clazz, Map<String, File> files, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete, Map<String, String> headers) {

        Map<String, RequestBody> requestBody = createPartFromObject(item);
        List<MultipartBody.Part> partList = createFilePart(files);
        Observable<ResponseBody> post = headers == null || headers.isEmpty()
                ? CiHttpClient.getHttpService(baseUrl).post(apiPath, requestBody, partList)
                : CiHttpClient.getHttpService(baseUrl).post(apiPath, requestBody, partList, headers);

        bindSubscriber(post, clazz, success, error, complete);
    }

    <TResponseContent>
    void post(String apiPath, Map<String, String> item, Class<TResponseContent> clazz, Map<String, File> files, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete, Map<String, String> headers) {

        Map<String, RequestBody> requestBody = createPartFromMap(item);
        List<MultipartBody.Part> partList = createFilePart(files);
        Observable<ResponseBody> post = headers == null || headers.isEmpty()
                ? CiHttpClient.getHttpService(baseUrl).post(apiPath, requestBody, partList)
                : CiHttpClient.getHttpService(baseUrl).post(apiPath, requestBody, partList, headers);

        bindSubscriber(post, clazz, success, error, complete);
    }

    <TResponseContent>
    void post(String apiPath, Class<TResponseContent> clazz, Map<String, File> files, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete, Map<String, String> headers) {

        List<MultipartBody.Part> partList = createFilePart(files);
        Observable<ResponseBody> post = headers == null || headers.isEmpty()
                ? CiHttpClient.getHttpService(baseUrl).post(apiPath, partList)
                : CiHttpClient.getHttpService(baseUrl).post(apiPath, partList, headers);

        bindSubscriber(post, clazz, success, error, complete);
    }

    <TResponseContent>
    void put(String apiPath, Object item, Class<TResponseContent> clazz, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete, Map<String, String> headers) {

        Observable<ResponseBody> put = headers == null || headers.isEmpty()
                ? CiHttpClient.getHttpService(baseUrl).put(apiPath, item)
                : CiHttpClient.getHttpService(baseUrl).put(apiPath, item, headers);

        bindSubscriber(put, clazz, success, error, complete);
    }

    <TResponseContent>
    void put(String apiPath, Object item, Class<TResponseContent> clazz, Map<String, File> files, Consumer<TResponseContent> success, Consumer<? super Throwable> error, Action complete, Map<String, String> headers) {

        Map<String, RequestBody> requestBody = createPartFromObject(item);
        List<MultipartBody.Part> partList = createFilePart(files);

        Observable<ResponseBody> put = headers == null || headers.isEmpty()
                ? CiHttpClient.getHttpService(baseUrl).put(apiPath, requestBody, partList)
                : CiHttpClient.getHttpService(baseUrl).put(apiPath, requestBody, partList, headers);

        bindSubscriber(put, clazz, success, error, complete);
    }


    private <T> void bindSubscriber(Observable<ResponseBody> observable, final Class<T> clazz, final Consumer<T> success, Consumer<? super Throwable> error, Action complete){

        Consumer<ResponseBody> successConsumer;
        if (success != null)
            successConsumer = new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody response) throws Exception {
                success.accept(BaseRepository.this.parseToJsonObject(response, clazz));
            }
        };
        else
            successConsumer = defaultSuccess;

        if(error == null)
            error = defaultError;

        if(complete == null)
            complete = defaultCompleted;

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(successConsumer, error, complete);
    }

    private <T> T parseToJsonObject(ResponseBody response, Class<T> clazz) {
        if (clazz == null || clazz == HttpNoContentResponse.class)
            return null;
        try {
            String data = response.string();
            if (clazz == String.class)
                return (T) data;
            T val = CiHttpClient.getGson().fromJson(data, clazz);
            return val;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    private RequestBody createPartFromField(String value) {
        return RequestBody.create(MultipartBody.FORM, value);
    }

    @NonNull
    private Map<String, RequestBody> createPartFromMap(Map<String, String> values) {
        Map<String, RequestBody> bodyMap = new HashMap<>();
        for (Map.Entry<String, String> ent : values.entrySet()) {
            bodyMap.put(ent.getKey(), createPartFromField(ent.getValue()));
        }
        return bodyMap;
    }

    @NonNull
    private <T> Map<String, RequestBody> createPartFromObject(T item) {
        Map<String, RequestBody> bodyMap = new HashMap<>();

        Field[] fields = item.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(!field.isAccessible())
                continue;
            try {
                Object value = field.get(item);
                bodyMap.put(field.getName(), createPartFromField(value instanceof String ? (String) value : value.toString()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return bodyMap;
    }

    @NonNull
    private <T> Map<String, String> createBodyFromObject(T item) {
        Map<String, String> bodyMap = new HashMap<>();

        Method[] methods = item.getClass().getMethods();

        for (Method m : methods) {
            String name = m.getName();
            if (name.startsWith("get") && !name.equalsIgnoreCase("getClass"))
                try {
                    String field = name.replace("get", "");
                    field = field.substring(0, 1).toLowerCase() + field.substring(1, field.length() - 1);
                    bodyMap.put(field, m.invoke(item).toString());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
        }
        return bodyMap;
    }

    @NonNull
    private MultipartBody.Part createFilePart(String partName, File file) {

        ContentResolver resolver = PBSBApplication.getInstance().getContentResolver();
        //create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse(partName), file);

        //MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    @NonNull
    private List<MultipartBody.Part> createFilePart(Map<String, File> files) {

        List<MultipartBody.Part> parts = new ArrayList<>();
        for (Map.Entry<String, File> fileEntry : files.entrySet()) {
            parts.add(createFilePart(fileEntry.getKey(), fileEntry.getValue()));
        }
        return parts;
    }
}
