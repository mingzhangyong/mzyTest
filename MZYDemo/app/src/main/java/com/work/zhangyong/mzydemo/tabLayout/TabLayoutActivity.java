package com.work.zhangyong.mzydemo.tabLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.work.zhangyong.mzydemo.R;
import com.work.zhangyong.mzydemo.toolbar.ToolbarActivity;

/**
 * Created by zhangyong on 2016/10/11.
 */

public class TabLayoutActivity extends ToolbarActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.tab_layout;
    }

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridView = (GridView) findViewById(R.id.grid_go_somewhere);


    }




    class gridAdapater extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
