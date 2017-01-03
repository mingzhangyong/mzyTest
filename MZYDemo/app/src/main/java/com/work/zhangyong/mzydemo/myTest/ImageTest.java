package com.work.zhangyong.mzydemo.myTest;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.work.zhangyong.mzydemo.R;
import com.work.zhangyong.mzydemo.toolbar.ToolbarActivity;


/**
 * Created by zhangyong on 2016/10/12.
 */

public class ImageTest extends ToolbarActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.image_test;
    }

    private MyImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final EditText editText = (EditText) findViewById(R.id.edit_set_ratio);
        imageView = (MyImageView) findViewById(R.id.my_test_img);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    if (editText.length() == 0) {
                        hideKeyboard();
                    } else {
                        String sRatio = editText.getText().toString();
                        Float fRatio = new Float(sRatio);
                        imageView.setRatio(fRatio);
                        hideKeyboard();
                    }
                }
                return false;
            }
        });



       /* ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        int hight = lp.height;
        int w = lp.width;

        Log.i("mzy","hight == "+w);


        int

                width =View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);

        int

                height =View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);

        imageView.measure(width,height);







                width=imageView.getMeasuredWidth();

        lp.height = (int) (width*0.1);
        imageView.setLayoutParams(lp);
        imageView.setMaxHeight((int) (width*0.1));

        Log.i("mzy","hight 1111== "+width);




        final ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                vto.removeOnPreDrawListener(this);
                int height = imageView.getMeasuredHeight();
                int width = imageView.getMeasuredWidth();
                return true;
            }
        });
*/
    }

    @Override
    protected void onResume() {
        super.onResume();
       /* ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        int hight = lp.height;
        int w = lp.width;
        Log.i("mzy","resume hight "+hight);*/
    }
}
