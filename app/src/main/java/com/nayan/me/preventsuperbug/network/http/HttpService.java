package com.nayan.me.preventsuperbug.network.http;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface HttpService {

    @GET
    Observable<ResponseBody> get(@Url String url);

    @GET
    Observable<ResponseBody> get(@Url String url, @HeaderMap Map<String, String> headers);

    @POST
    Observable<ResponseBody> post(@Url String url, @Body /*SuppressWarnings("rawtypes")*/ Object content);

    @POST
    Observable<ResponseBody> post(@Url String url, @Body Object content, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap(encoded = true) Map<String, String> content);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap(encoded = true) Map<String, String> content, @HeaderMap Map<String, String> headers);


    @Multipart
    @POST
    Observable<ResponseBody> post(@Url String url, @Part List<MultipartBody.Part> filePartMap);

    @Multipart
    @POST
    Observable<ResponseBody> post(@Url String url, @Part List<MultipartBody.Part> filePartMap, @HeaderMap Map<String, String> headers);


    @Multipart
    @POST
    Observable<ResponseBody> post(@Url String url, @PartMap Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

    @Multipart
    @POST
    Observable<ResponseBody> post(@Url String url, @PartMap Map<String, RequestBody> partMap, @Part MultipartBody.Part file, @HeaderMap Map<String, String> headers);


    @Multipart
    @POST
    Observable<ResponseBody> post(@Url String url, @PartMap Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> filePartMap);

    @Multipart
    @POST
    Observable<ResponseBody> post(@Url String url, @PartMap Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> filePartMap, @HeaderMap Map<String, String> headers);


    @PUT
    Observable<ResponseBody> put(@Url String url, @Body Object content);

    @PUT
    Observable<ResponseBody> put(@Url String url, @Body Object content, @HeaderMap Map<String, String> headers);


    @Multipart
    @PUT
    Observable<ResponseBody> put(@Url String url, @Part List<MultipartBody.Part> filePartMap);

    @Multipart
    @PUT
    Observable<ResponseBody> put(@Url String url, @Part List<MultipartBody.Part> filePartMap, @HeaderMap Map<String, String> headers);


    @Multipart
    @PUT
    Observable<ResponseBody> put(@Url String url, @PartMap Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

    @Multipart
    @PUT
    Observable<ResponseBody> put(@Url String url, @PartMap Map<String, RequestBody> partMap, @Part MultipartBody.Part file, @HeaderMap Map<String, String> headers);


    @Multipart
    @PUT
    Observable<ResponseBody> put(@Url String url, @PartMap Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> filePartMap);

    @Multipart
    @PUT
    Observable<ResponseBody> put(@Url String url, @PartMap Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> filePartMap, @HeaderMap Map<String, String> headers);


    @DELETE
    Observable<ResponseBody> delete(@Url String url);

    @DELETE
    Observable<ResponseBody> delete(@Url String url, @HeaderMap Map<String, String> headers);
}
