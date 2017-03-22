package com.work.zhangyong.mzydemo.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangyong on 2017/3/1.
 */

public class GetStringOkhttp extends BaseOkhttp {

    private String url ;
    private Request request;

    public GetStringOkhttp(String url){
        this.url = url;
        request = builder
                .get()
                .url(url)
                .build();
    }


    String whatDidIGet;
    public String callHttp() {
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                whatDidIGet = e.toString();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                whatDidIGet = response.body().string();

            }
        });
        return whatDidIGet;
    }
}
