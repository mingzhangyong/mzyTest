package com.work.zhangyong.mzydemo.okhttp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhangyong on 2017/3/1.
 */

public class TestGetOkHttpService extends IntentService {
    public static final String GET_STRING_ACTION = "com.mzy.GET_STRING_ACTION";

    public TestGetOkHttpService(String name) {
        super(name);
    }

    public static void startGetString(Context context , Messenger messenger){
        Intent intent = new Intent(context,TestGetOkHttpService.class);
        intent.setAction(GET_STRING_ACTION);
        intent.putExtra("test",messenger);
        context.startService(intent);
    }

    private GetStringOkhttp getStringOkhttp;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent == null){
            return;
        }
        final String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        if(GET_STRING_ACTION.equals(bundle)){
            handleActionGetOkHttp(bundle);
        }
    }

    private void handleActionGetOkHttp(Bundle bundle) {
        if(bundle != null){
            getStringOkhttp = new GetStringOkhttp(Config.mBaseUrl+"login?userName=sda&password=123");
            Log.e("mzy","what did i get "+ getStringOkhttp.callHttp());
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
