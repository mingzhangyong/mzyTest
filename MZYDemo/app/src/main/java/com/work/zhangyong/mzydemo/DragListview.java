package com.work.zhangyong.mzydemo;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DragListview extends ListActivity {
    MyAdapter adapter;
    TouchInterceptor list;
    List<String> arrayText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //(TouchInterceptor) getListView();//
        list = (TouchInterceptor)findViewById(android.R.id.list);
        getText();

        adapter = new MyAdapter(this);
        setListAdapter(adapter);

        list.setDropListener(mDropListener);
        //   list.setRemoveListener(mRemoveListener);
    }
    public void getText(){
        arrayText = new ArrayList<String>();
        arrayText.add("传奇");
        arrayText.add("红豆");
        arrayText.add("流年");
        arrayText.add("棋子");
    }

    //交换listview的数据
    private TouchInterceptor.DropListener mDropListener =
            new TouchInterceptor.DropListener() {
                public void drop(int from, int to) {
                    String item = arrayText.get(from);
                    arrayText.remove(item);//.remove(from);
                    arrayText.add(to, item);
                    adapter.notifyDataSetChanged();
                }
            };

    private TouchInterceptor.RemoveListener mRemoveListener =
            new TouchInterceptor.RemoveListener() {
                public void remove(int which) {
                }
            };

    class MyAdapter extends BaseAdapter{
        private LayoutInflater mInflater;
        Context mContext;
        public MyAdapter(Context c){
            mInflater = LayoutInflater.from(c);
        }
        public int getCount() {
            return arrayText.size();
        }

        public Object getItem(int arg0) {
            return arrayText.get(arg0);
        }

        public long getItemId(int arg0) {
            return arg0;
        }

        public View getView(int arg0, View contentView, ViewGroup arg2) {
            ImageView img;
            TextView text;
            if(contentView==null){
                contentView = mInflater.inflate(R.layout.list_layout, null);
                //contentView = mInflater.inflate(R.layout.list_layout,null);
            }
            img = (ImageView)contentView.findViewById(R.id.img);
            img.setBackgroundResource(R.drawable.m);
            text = (TextView)contentView.findViewById(R.id.text);
            text.setText(arrayText.get(arg0).toString());

            return contentView;
        }

    }
}