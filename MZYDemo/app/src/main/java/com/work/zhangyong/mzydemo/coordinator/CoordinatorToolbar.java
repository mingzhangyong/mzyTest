package com.work.zhangyong.mzydemo.coordinator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.work.zhangyong.mzydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyong on 2016/8/8.
 */
public class CoordinatorToolbar extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Integer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        for (int i = 0; i <200 ; i++) {
            list.add(i);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this,list);
        adapter.setOnItemClickListener(new MyRecyclerViewHolder.RecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(CoordinatorToolbar.this, "点我干嘛，我是" + position, Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);


        Toolbar myToolBar = (Toolbar) findViewById(R.id.mytoolbar);
        myToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色


    }


}
