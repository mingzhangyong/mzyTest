package com.work.zhangyong.mzydemo.divider;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.TextView;

import com.work.zhangyong.mzydemo.R;

/**
 * Created by zhangyong on 2016/7/19.
 */
public class TBActivity extends Activity {

private TBLayout mTblayout;
    private ScrollView mHeader;
    private ScrollView mFooter;
    private TextView mHeaderContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tb_layout);
        mTblayout = (TBLayout) findViewById(R.id.tblayout);
        mTblayout.setOnPullListener(new TBLayout.OnPullListener() {
            @Override
            public boolean headerFootReached(MotionEvent event) {
                if (mHeader.getScrollY() + mHeader.getHeight() >= mHeaderContent
                        .getHeight()) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean footerHeadReached(MotionEvent event) {
                if (mFooter.getScrollY() == 0) {
                    return true;
                }
                return false;
            }
        });
        mTblayout.setOnContentChangeListener(new TBLayout.OnPageChangedListener() {
            @Override
            public void onPageChanged(int stub) {
                switch (stub) {
                    case TBLayout.SCREEN_HEADER:
                        mHeader.scrollTo(0, 0);
                        break;
                    case TBLayout.SCREEN_FOOTER:
                        mFooter.scrollTo(0,0);

                        break;
                }
            }
        });
        mHeader = (ScrollView) findViewById(R.id.header);
        mFooter = (ScrollView) findViewById(R.id.footer);
        mHeaderContent = (TextView) mHeader.getChildAt(0);

    }
}
