package com.work.zhangyong.mzydemo.viewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.work.zhangyong.mzydemo.R;

import java.util.ArrayList;

public class ViewPagerActivity extends FragmentActivity {

    ViewPager pager = null;

    ArrayList<Fragment> fragmentContainter = new ArrayList<>();
    ArrayList<String> titleContainer = new ArrayList<String>();

    TextView t1,t2,t3,t4;

    ViewPagerAdapter viewPagerAdapter;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout_activity);

        initTextView();
        initViewPager();
    }

    private void initViewPager() {
        pager = (ViewPager) findViewById(R.id.viewpager);
        Fragment f1 = new Fragmen1();
        Fragment f2 = new Fragmen2();
        Fragment f3 = new Fragmen3();
        Fragment f4 = new Fragmen4();
        fragmentContainter.add(f1);
        fragmentContainter.add(f2);
        fragmentContainter.add(f3);
        fragmentContainter.add(f4);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentContainter);

        pager.setAdapter(viewPagerAdapter);
        pager.setCurrentItem(0);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initTextView() {
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);

        t1.setOnClickListener(new TListener(0));
        t2.setOnClickListener(new TListener(1));
        t3.setOnClickListener(new TListener(2));
        t4.setOnClickListener(new TListener(3));

        String a= "abababababab" ;
        String pre = "";
        String pre1 = "";
        String first ="";
        for (int i = 0; i <a.length() ; i++) {
            Log.i("mzy", "-----"+a.charAt(i));

            if(i > 4 && i%2 == 0){
                if( pre.equals((""+a.charAt(i-1)+a.charAt(i)))){

                }else{
                    first = first + pre;
                }
            }

            if(i>0)
            pre = ""+a.charAt(i-1)+a.charAt(i);
        }
        Log.i("mzy","first  "+first);

    }
    public class TListener implements View.OnClickListener{
        private int index = 0;

        public TListener(int i){
            index = i;
        }

        @Override
        public void onClick(View v) {
            pager.setCurrentItem(index);
        }
    }

}
