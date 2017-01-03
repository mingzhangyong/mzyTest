package com.work.zhangyong.mzydemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import java.lang.ref.WeakReference;

/**
 * Created by zhangyong on 2016/7/15.
 */
public class MZYList extends ListView {
    Context context;
    WeakReference<MainActivity> mainactivity;

    public MZYList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
mainactivity = new WeakReference<>((MainActivity)context);
    }


    /*@Override
    public boolean onTouchEvent(MotionEvent event) {



        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
            {

                int  i = pointToPosition((int)ev.getX(), (int)ev.getY());
                mainactivity.get().toDoDown(i);
                break;
            }
            case MotionEvent.ACTION_UP:{

                int  i = pointToPosition((int)ev.getX(), (int)ev.getY());
                mainactivity.get().toDoUp(i);
                break;
            }
        }


        return super.onInterceptTouchEvent(ev);
    }*/
}
