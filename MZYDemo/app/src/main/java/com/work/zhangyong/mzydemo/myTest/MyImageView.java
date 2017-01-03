package com.work.zhangyong.mzydemo.myTest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zhangyong on 2016/10/12.
 */

public class MyImageView extends ImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float ratio = 0f;
    public void setRatio(float ratio){
        this.ratio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1.从widthMeasureSpec中反向获取父VIew计算好的size
        int width = MeasureSpec.getSize(widthMeasureSpec);
//      LogUtil.e(this, "width: "+width);
        //2.根据宽高比和width，计算出对应的height
        if(ratio!=0){
            float height = width*ratio;
            //3.重新组建heightMeasureSpec，传递给super.onMeasure
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height,MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
