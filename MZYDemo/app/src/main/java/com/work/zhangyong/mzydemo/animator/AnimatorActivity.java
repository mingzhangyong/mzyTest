package com.work.zhangyong.mzydemo.animator;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.work.zhangyong.mzydemo.R;

import java.util.ArrayList;
import java.util.List;

public class AnimatorActivity extends AppCompatActivity {

    private ImageView imageView;
    private boolean toRight = true;
    private boolean isPlaying = false;


    private int[] imgsId = {R.id.a, R.id.b, R.id.c, R.id.d, R.id.e, R.id.f, R.id.g, R.id.h, R.id.i, R.id.j, R.id.k, R.id.l, R.id.m, R.id.n, R.id.o, R.id.q};

    private List<ImageView> imgList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_layout);


        imageView = (ImageView) findViewById(R.id.p);

        Log.i("mzy", "1*Math.sin(3.14)==  " + 1 * Math.sin(3.14));
        for (int i = 0; i < imgsId.length; i++) {
            ImageView img = (ImageView) findViewById(imgsId[i]);
            imgList.add(img);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isPlaying) {
                    if (toRight) {
                        for (int i = 0; i < imgsId.length; i++) {
                            Log.i("mzy", "(float)(500*Math.sin(i*1/3*3.14))" + (float) (500 * Math.sin(i * 1 / 3 * 3.14)));
                            ObjectAnimator animator = ObjectAnimator.ofFloat(imgList.get(i), "translationX", 0f, (float) (200 * Math.cos(i * 2 * 3.14 / (imgsId.length))));
                            animator.setDuration(1000);
                            animator.setInterpolator(new BounceInterpolator());
                            animator.start();
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgList.get(i), "translationY", 0f, (float) (200 * Math.sin(i * 2 * 3.14 / (imgsId.length))));
                            animator1.setDuration(1000);
                            animator1.setInterpolator(new BounceInterpolator());
                            animator1.start();
                        }


                        toRight = false;
                    } else {
                        for (int i = 0; i < imgsId.length; i++) {
                            Log.i("mzy", "(float)(500*Math.sin(i*1/3*3.14))" + (float) (500 * Math.sin(i * 1 / 3 * 3.14)));
                            ObjectAnimator animator = ObjectAnimator.ofFloat(imgList.get(i), "translationX", (float) (200 * Math.cos(i * 2 * 3.14 / (imgsId.length))), 0f);
                            animator.setDuration(1000);
                            animator.start();
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgList.get(i), "translationY", (float) (200 * Math.sin(i * 2 * 3.14 / (imgsId.length))), 0f);
                            animator1.setDuration(1000);
                            animator1.start();

                        }


                        if (i < imgsId.length) {
                            for (int j = 0; j < imgsId.length; j++) {
                                ObjectAnimator animator = ObjectAnimator.ofFloat(imgList.get(j), "translationX", (float) (200 * Math.cos((j + i) * 2 * 3.14 / (imgsId.length))), (float) (200 * Math.cos((j + 1 + i) * 2 * 3.14 / (imgsId.length))));
                                animator.setDuration(1000);
                                animator.start();
                                ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgList.get(j), "translationY", (float) (200 * Math.sin((j + i) * 2 * 3.14 / (imgsId.length))), (float) (200 * Math.sin((j + 1 + i) * 2 * 3.14 / (imgsId.length))));
                                animator1.setDuration(1000);
                                animator1.start();

                            }

                            handler.post(runnable);
                            isPlaying = true;
                            for (int i = 0; i < imgsId.length; i++) {
                                Log.i("mzy", "iiii?" + i);
                                ObjectAnimator animator = ObjectAnimator.ofFloat(imgList.get(0), "translationX", (float) (200 * Math.cos((i + 1) * 2 * 3.14 / (imgsId.length))), (float) (200 * Math.cos((i + 2) * 2 * 3.14 / (imgsId.length))));
                                animator.setDuration(1000);
                                animator.start();
                                ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgList.get(0), "translationY", (float) (200 * Math.sin((i + 1) * 2 * 3.14 / (imgsId.length))), (float) (200 * Math.sin((i + 2) * 2 * 3.14 / (imgsId.length))));
                                animator1.setDuration(1000);
                                animator1.start();
                            }

                        }
                    }

                }else{
                    Intent intent = new Intent(AnimatorActivity.this, AnimatorWidget.class);
                    startActivity(intent);
                }
            }
        });

        }
    int i = 0;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (i < imgsId.length) {
                for (int j = 0; j < imgsId.length; j++) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(imgList.get(j), "translationX", (float) (200 * Math.cos((j + i) * 2 * 3.14 / (imgsId.length))), (float) (200 * Math.cos((j + 1 + i) * 2 * 3.14 / (imgsId.length))));
                    animator.setDuration(100);
                    animator.start();
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgList.get(j), "translationY", (float) (200 * Math.sin((j +i) * 2 * 3.14 / (imgsId.length))), (float) (200 * Math.sin((j + 1+i) * 2 * 3.14 / (imgsId.length))));
                    animator1.setDuration(100);
                    animator1.start();

                }
                handler.postDelayed(runnable, 100);
            }else{
                isPlaying = false;
                toRight = true;
                i = 0;
            }
            i++;
        }
    };
    }

