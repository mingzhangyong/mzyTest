package com.work.zhangyong.mzydemo.animator;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.work.zhangyong.mzydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyong on 2016/5/24.
 */
public class AnimatorWidget extends Activity {

    TextView button;
    TextView button2;
    TextView button3;
    TextView button4;

    List<TextView> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_widget);

        button = (TextView) findViewById(R.id.button);
        button2 = (TextView) findViewById(R.id.button2);
        button3 = (TextView) findViewById(R.id.button3);
        button4 = (TextView) findViewById(R.id.button4);
        list.add(button2);
        list.add(button3);
        list.add(button4);
        button.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button: {
                    myAnimator();
                    break;
                }
                default: {
                    Log.i("mzy", "id  ==" + v.getId());
                    break;
                }
            }
        }
    };

    private void myAnimator() {
        float y = button.getHeight()/2;
        for(int i = 0;i<list.size();i++){
            list.get(i).setVisibility(View.VISIBLE);
            //平移动画
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(list.get(i), "translationY", 0f, y+list.get(i).getHeight()/2);
            objectAnimator.setDuration(1000);
            objectAnimator.start();

            //透明度变化
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(list.get(i), "alpha", 0f,1f);
            objectAnimator1.setDuration(1000);
            objectAnimator1.start();
            y = y + list.get(i).getHeight();
        }
        /*button2.setVisibility(View.VISIBLE);
        //平移动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button2, "translationY", 0f, button.getHeight());
        objectAnimator.setDuration(100);
        objectAnimator.start();

        //透明度变化
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(button2, "alpha", 0f,0.5f);
        objectAnimator1.setDuration(100);
        objectAnimator1.start();*/
    }
}
