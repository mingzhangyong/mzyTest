package com.work.zhangyong.buglyandtinker;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by zhangyong on 2016/10/24.
 */

public class BugLyAndTinkerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900056991", false);
    }
}
