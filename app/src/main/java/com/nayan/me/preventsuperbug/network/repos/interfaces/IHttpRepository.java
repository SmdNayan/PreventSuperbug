package com.nayan.me.preventsuperbug.network.repos.interfaces;

import android.util.Pair;


import com.nayan.me.preventsuperbug.network.repos.interfaces.common.IGetOnlyRepository;
import com.nayan.me.preventsuperbug.network.repos.interfaces.common.IPostOnlyRepository;

import java.util.Map;

public interface IHttpRepository extends IGetOnlyRepository, IPostOnlyRepository {
    IHttpRepository putHeader(String key, String value);

    IHttpRepository putHeader(Pair<String, String> header);

    IHttpRepository putHeaders(Map<String, String> headers);

    void clearHeaders();
}
