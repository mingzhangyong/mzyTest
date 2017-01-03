package com.work.zhangyong.mzydemo.gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.work.zhangyong.mzydemo.R;

/**
 * Demo描述:
 * HorizontalScrollView的简单使用
 *
 * 在Google文档中Gallery已经弃用.推荐使用HorizontalScrollView和ViewPager.
 * ViewPager用过不少,HorizontalScrollView倒是很少使用.所以在此学习.
 *
 * Demo说明:
 * 1 布局文件很简单,在HorizontalScrollView中嵌套了一个LinearLayout
 * 2 在代码中只需将自定义的View添加到LinearLayout中即可.
 * 这样就可以实现水平滑动了.
 *
 */
public class GalleryActivity extends Activity {
    private Context mContext;
    private int [] mPhotosIntArray;
    private LayoutInflater mLayoutInflater;
    private LinearLayout mGalleryLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);
        initView();
        initData();
    }

    private void initView(){
        mContext=this;
        mGalleryLinearLayout=(LinearLayout) findViewById(R.id.galleryLinearLayout);
        mLayoutInflater=LayoutInflater.from(mContext);
    }
    private void initData(){
        mPhotosIntArray=new int[]{R.drawable.m,R.drawable.m,R.drawable.m,R.drawable.m,R.drawable.m,R.drawable.m,R.drawable.m,R.drawable.m};
        View itemView=null;
        ImageView imageView=null;
        TextView textView;
        for (int i = 0; i < mPhotosIntArray.length; i++) {
            itemView=mLayoutInflater.inflate(R.layout.gallery_item, null);
            imageView=(ImageView) itemView.findViewById(R.id.imageView);
            textView=(TextView) itemView.findViewById(R.id.textView);
            imageView.setImageResource(mPhotosIntArray[i]);
            textView.setText("This is "+(i+1));
            mGalleryLinearLayout.addView(itemView);
        }
    }

}