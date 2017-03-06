package com.work.zhangyong.mzydemo.wuziqi;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.work.zhangyong.mzydemo.R;
import com.work.zhangyong.mzydemo.toolbar.ToolbarActivity;

import java.util.List;

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
        final WuziqiPanel panel = (WuziqiPanel) findViewById(R.id.panel);
        Button check = (Button) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panel.checkIfWIn(new WuziqiPanel.OnChanged() {
                    @Override
                    public void onChanged(List<Point> whiteArray, List<Point> blackArray) {
                        Toast.makeText(mContext, "white "+whiteArray.size(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
