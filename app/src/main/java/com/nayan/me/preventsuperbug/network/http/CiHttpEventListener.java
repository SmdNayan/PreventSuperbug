package com.nayan.me.preventsuperbug.network.http;


import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Protocol;


public class CiHttpEventListener extends EventListener {
    @Override
    public void callStart(Call call) {
        super.callStart(call);
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, @Nullable Proxy proxy, @Nullable Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
    }

    @Override
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, @Nullable Proxy proxy, @Nullable Protocol protocol, @Nullable IOException ioe) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
    }

    @Override
    public void connectionAcquired(Call call, Connection connection) {
        super.connectionAcquired(call, connection);
    }

    @Override
    public void connectionReleased(Call call, Connection connection) {
        super.connectionReleased(call, connection);
    }

    @Override
    public void callEnd(Call call) {
        super.callEnd(call);
    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        super.callFailed(call, ioe);
    }
}
