package com.work.zhangyong.buglyandtinker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.hello);
        CrashReport.initCrashReport(this);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrashReport.testJavaCrash();

                Log.i("mzy","hahahahaweeew23232");
            }
        });
    }
}
