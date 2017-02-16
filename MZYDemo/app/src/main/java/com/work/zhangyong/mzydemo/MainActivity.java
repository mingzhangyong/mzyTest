package com.work.zhangyong.mzydemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.work.zhangyong.mzydemo.animator.AnimatorActivity;
import com.work.zhangyong.mzydemo.coordinator.CoordinatorActivityAppBar;
import com.work.zhangyong.mzydemo.coordinator.CoordinatorActivityFloatingButton;
import com.work.zhangyong.mzydemo.coordinator.CoordinatorToolbar;
import com.work.zhangyong.mzydemo.dialog.MyDialog;
import com.work.zhangyong.mzydemo.divider.TBActivity;
import com.work.zhangyong.mzydemo.gallery.GalleryActivity;
import com.work.zhangyong.mzydemo.myTest.ImageTest;
import com.work.zhangyong.mzydemo.toolbar.ToolbarActivity;
import com.work.zhangyong.mzydemo.viewPager.ViewPagerActivity;
import com.work.zhangyong.mzydemo.wuziqi.WuziqiActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ToolbarActivity {
    public static final int SELECT_PIC_KITKAT = 0;
    public static final int IMAGE_REQUEST_CODE = 1;
    public String filePath = Environment.getExternalStorageDirectory() + "/headImg.jpg";


    private List<Integer> list = new ArrayList<>();
    MyAdapter adapter;
    MZYList listView;
    ImageView img;
    List a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView helloWorld = (TextView) findViewById(R.id.hello_world);
        img = (ImageView) findViewById(R.id.img);
        listView = (MZYList) findViewById(R.id.list_view);


        if (null != a && a.size() > 0) {
            Log.i("mzy", " im ok ");
        } else {
            Log.i("mzy", " im ok ");
        }


        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);

        setTitle("我是谁？");

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        if (helloWorld != null) {
            helloWorld.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, TBActivity.class);
                    startActivity(intent);
                    /*Intent intentFromGallery = new Intent();
                    intentFromGallery.setType("image*//*"); // 设置文件类型
                    intentFromGallery
                            .setAction(Intent.ACTION_GET_CONTENT);
    //                                startActivityForResult(intentFromGallery,IMAGE_REQUEST_CODE);
                    intentFromGallery.addCategory(Intent.CATEGORY_OPENABLE);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        startActivityForResult(intentFromGallery, SELECT_PIC_KITKAT);
                    } else {
                        startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                    }*/
                }
            });
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (list.get(position)) {
                    case 0: {
                        Toast.makeText(MainActivity.this,"sdasdasdasdasd",Toast.LENGTH_LONG);
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        Intent intentFromGallery = new Intent();
                        intentFromGallery.setType("image/*"); // 设置文件类型
                        intentFromGallery
                                .setAction(Intent.ACTION_GET_CONTENT);
//                                startActivityForResult(intentFromGallery,IMAGE_REQUEST_CODE);
                        intentFromGallery.addCategory(Intent.CATEGORY_OPENABLE);
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                            startActivityForResult(intentFromGallery, SELECT_PIC_KITKAT);
                        } else {
                            startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                        }
                        break;
                    }
                    case 2: {
                        Intent intent = new Intent(MainActivity.this, DragListview.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: {
                        Intent intent = new Intent(MainActivity.this, TBActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 4: {
                        Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 5: {
                        Intent intent = new Intent(MainActivity.this, CoordinatorActivityAppBar.class);
                        startActivity(intent);
                        break;
                    }
                    case 6: {
                        Intent intent = new Intent(MainActivity.this, CoordinatorActivityFloatingButton.class);
                        startActivity(intent);
                        break;
                    }
                    case 7: {
                        Intent intent = new Intent(MainActivity.this, CoordinatorToolbar.class);
                        startActivity(intent);
                        break;
                    }
                    case 8: {
                        MyDialog dialog = new MyDialog(mContext);
                        dialog.show();
                        break;
                    }
                    case 9: {
                        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                        startActivity(intent);
                        break;
                    }


                    case 10: {

                        Bitmap btm = BitmapFactory.decodeResource(getResources(),
                                R.drawable.m);
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                                MainActivity.this).setSmallIcon(R.drawable.m)
                                .setContentTitle("5 new message")
                                .setContentText("twain@android.com");
                        mBuilder.setTicker("New message");//第一次提示消息的时候显示在通知栏上
                        mBuilder.setNumber(12);
                        mBuilder.setLargeIcon(btm);
                        mBuilder.setAutoCancel(true);//自己维护通知的消失

//构建一个Intent
                        Intent resultIntent = new Intent(MainActivity.this,
                                ViewPagerActivity.class);
//封装一个Intent
                        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                                MainActivity.this, 0, resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
// 设置通知主题的意图
                        mBuilder.setContentIntent(resultPendingIntent);
//获取通知管理器对象
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder.build());

                        break;
                    }
                    case 11:{
                        Intent i = new Intent(MainActivity.this, ImageTest.class);
                        startActivity(i);
                        break;
                    }
                    case 12:{
                        Intent i = new Intent(MainActivity.this, AnimatorActivity.class);
                        startActivity(i);
                        break;
                    }
                    case 13:{
                        Intent i = new Intent(MainActivity.this, WuziqiActivity.class);
                        startActivity(i);
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }

        switch (requestCode) {
            case IMAGE_REQUEST_CODE:
                startPhotoZoom(data.getData());
                break;
            case SELECT_PIC_KITKAT:
                startPhotoZoom(data.getData());
                break;

            case 2: {                //收到图片、显示出来
                File file = new File(filePath);
                if (file.exists()) {
                    Bitmap bm = BitmapFactory.decodeFile(filePath);
                    img.setImageBitmap(bm);
                }
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            String url = uri.getPath();
            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "")), "image/*");

        } else {
            intent.setDataAndType(uri, "image/*");

        }
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("scale", true);
        File tempFile = new File(Environment.getExternalStorageDirectory(), "headImg.jpg");
        tempFile.getParentFile().mkdirs();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }


    public class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void removeItemAtPosition(int position) {
            list.remove(position);
            notifyDataSetChanged();
        }

        public void addItemAtPosition(int obj, int position) {
            list.add(position, obj);
            notifyDataSetChanged();
        }

        /**
         * How many items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * Get the data item associated with the specified position in the data set.
         *
         * @param position Position of the item whose data we want within the adapter's
         *                 data set.
         * @return The data at the specified position.
         */
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        /**
         * Get the row id associated with the specified position in the list.
         *
         * @param position The position of the item within the adapter's data set whose row id we want.
         * @return The id of the item at the specified position.
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * Get a View that displays the data at the specified position in the data set. You can either
         * create a View manually or inflate it from an XML layout file. When the View is inflated, the
         * parent View (GridView, ListView...) will apply default layout parameters unless you use
         * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
         * to specify a root view and to prevent attachment to the root.
         *
         * @param position    The position of the item within the adapter's data set of the item whose view
         *                    we want.
         * @param convertView The old view to reuse, if possible. Note: You should check that this view
         *                    is non-null and of an appropriate type before using. If it is not possible to convert
         *                    this view to display the correct data, this method can create a new view.
         *                    Heterogeneous lists can specify their number of view types, so that this View is
         *                    always of the right type (see {@link #getViewTypeCount()} and
         *                    {@link #getItemViewType(int)}).
         * @param parent      The parent that this view will eventually be attached to
         * @return A View corresponding to the data at the specified position.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.list_layout, null);
            }
            TextView text = (TextView) convertView.findViewById(R.id.text);
            ImageView img = (ImageView) convertView.findViewById(R.id.img);
            text.setText(list.get(position) + "");
            img.setImageResource(R.drawable.m);
            return convertView;
        }
    }


    /**
     * Called when a touch screen event was not handled by any of the views
     * under it.  This is most useful to process touch events that happen
     * outside of your window bounds, where there is no view to receive it.
     *
     * @param event The touch screen event being processed.
     * @return Return true if you have consumed the event, false if you haven't.
     * The default implementation always returns false.
     */
    private GestureDetector mGestureDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
            {

                String i = list.get(listView.pointToPosition((int)event.getX(), (int)event.getY()) - listView.getFirstVisiblePosition());
                Toast.makeText(MainActivity.this,"hhahaha  "+i,Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this, event.getX()+"," + event.getY(), Toast.LENGTH_LONG).show();

                break;
            }
            case MotionEvent.ACTION_UP:{

                Toast.makeText(MainActivity.this,event.getX()+"," + event.getY(),Toast.LENGTH_LONG).show();
                break;
            }
        }*/


        return false;
    }

    int downString;

    public void toDoDown(int i) {
        downString = (int) adapter.getItem(i);
        adapter.removeItemAtPosition(i);

    }

    public void toDoUp(int i) {
        adapter.addItemAtPosition(downString, i);
    }

}
