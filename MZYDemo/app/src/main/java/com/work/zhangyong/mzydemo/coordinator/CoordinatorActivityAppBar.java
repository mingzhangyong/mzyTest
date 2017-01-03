package com.work.zhangyong.mzydemo.coordinator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.work.zhangyong.mzydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyong on 2016/7/29.
 */
public class CoordinatorActivityAppBar extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Integer> list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.coordinator_layout);
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
                Toast.makeText(CoordinatorActivityAppBar.this, "点我干嘛，我是" + position, Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);


    }
}
