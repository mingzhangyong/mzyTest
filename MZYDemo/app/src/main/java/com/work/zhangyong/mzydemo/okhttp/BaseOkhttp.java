package com.work.zhangyong.mzydemo.okhttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangyong on 2017/3/1.
 */

public abstract class BaseOkhttp {
    private String mBaseUrl = "http://192.168.180.2:8080/okhttp/";
    protected OkHttpClient okHttpClient = new OkHttpClient();
    protected void setCookieManager() {
        okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            private Map<String, List<Cookie>> cookieStore = new HashMap<>();
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        }).build();
    }

    Request.Builder builder = new Request.Builder();

}
