package com.work.zhangyong.mzydemo.wuziqi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.work.zhangyong.mzydemo.R;
import com.work.zhangyong.mzydemo.toolbar.ToolbarActivity;

/**
 * Created by root on 17-2-10.
 */

public class WuziqiActivity extends ToolbarActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_wuziqi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WuziqiPanel wuziqiPanel = (WuziqiPanel) findViewById(R.id.wuziqi);
        Button button = (Button) findViewById(R.id.check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        wuziqiPanel.checkIfWIn(new WuziqiPanel.OnGameOver() {
            @Override
            public void gameOver(boolean isGameOver) {

            }
        });
    }
}
