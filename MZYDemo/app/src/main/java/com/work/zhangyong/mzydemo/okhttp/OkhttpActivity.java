package com.work.zhangyong.mzydemo.okhttp;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.zhangyong.mzydemo.R;
import com.work.zhangyong.mzydemo.toolbar.ToolbarActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhangyong on 2017/2/23.
 */

public class OkhttpActivity extends ToolbarActivity {

    private String mBaseUrl = "http://192.168.180.2:8080/okhttp/";
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_okhttp;
    }

    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCookieManager();
        Log.i("mzy","oncreate");
        TestGetOkHttpService.startGetString(OkhttpActivity.this,new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        }));
        /*Button button = (Button) findViewById(R.id.get_http);
        Button button1 = (Button) findViewById(R.id.doPost);
        Button button2 = (Button) findViewById(R.id.doDownload);
        Button button3 = (Button) findViewById(R.id.doPostFile);
        Button button4 = (Button) findViewById(R.id.doPostStringAndFile);
        textView = (TextView) findViewById(R.id.tv_get);
        imageView = (ImageView) findViewById(R.id.img_get);
        button.setOnClickListener(cliclListener);
        button1.setOnClickListener(cliclListener);
        button2.setOnClickListener(cliclListener);
        button3.setOnClickListener(cliclListener);
        button4.setOnClickListener(cliclListener);*/
    }

    




private View.OnClickListener cliclListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get_http :{
                doGetHttp();
                break;
            }
            case R.id.doPost:{
                doPost();
                break;
            }
            case R.id.doDownload:{
                doDownload();
                break;
            }
            case R.id.doPostFile:{
                doPostFile();
                break;
            }
            case R.id.doPostStringAndFile:{
                doPostStringAndFile();
                break;
            }

        }
    }
};

    private void setCookieManager() {
        okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            private Map<String, List<Cookie>> cookieStore = new HashMap<>();
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        }).build();
    }

    private void doGetHttp(){
        //1.拿到okHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();

        //2.构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .get()
                .url(mBaseUrl+"login?userName=sda&password=123")
                .build();
        callHttp(request);


    }

    private void callHttp(Request request) {
        //3.将Request封装为Call
        Call call = okHttpClient.newCall(request);


        //4.执行Call
                /*try {
                    Response response = call.execute(); //直接执行
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败回掉
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功回掉
                // Log.e("mzy","what did i get = "+response.body().string());
                final String res = response.body().string();
                //这里不能直接进行ui线程的操作，目的：支持大文件的下载，获取response流
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(res);
                    }
                });*/
            }
        });
    }

    private void doPost(){
        //1.拿到okHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();


        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"),"{username:mao,password:12345}");
        //2.构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mBaseUrl + "postString").post(requestBody).build();
        callHttp(request);
    }

    private void doPostFile(){
        //1.拿到okHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();

        File file = new File(Environment.getExternalStorageDirectory(),"faceImage.jpg");
        if(!file.exists()){
            Log.e("mzy",file.getAbsolutePath()+" not exist");
            return;
        }


        //mime type获取所有格式对应的mediaType
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"),file);


        CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(long byteWrited, long contentLength) {
                Log.i("mzy",byteWrited + "/" + contentLength);
            }
        });
        //2.构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mBaseUrl + "postFile").post(countingRequestBody).build();
        callHttp(request);
    }

    private void doPostStringAndFile(){
        //1.拿到okHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();

        File file = new File(Environment.getExternalStorageDirectory(),"faceImage.jpg");
        if(!file.exists()){
            Log.e("mzy",file.getAbsolutePath()+" not exist");
            return;
        }
        //mime type获取所有格式对应的mediaType
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"),file);

        //构造requestBody
        MultipartBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userName","mao")
                .addFormDataPart("password","123")
                .addFormDataPart("mPhoto","maoapian.jpg",requestBody).build();


        //2.构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mBaseUrl + "postStringAndFile").post(body).build();
        callHttp(request);
    }

    private void doDownload(){
        Request.Builder builder = new Request.Builder();
        final Request request = builder
                .get()
                .url(mBaseUrl+"files/mao.jpg")
                .build();

        //3.将Request封装为Call
        Call call = okHttpClient.newCall(request);


        //4.执行Call
                /*try {
                    Response response = call.execute(); //直接执行
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败回掉
                Log.e("mzy","doDownload err");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();

                    Log.i("mzy","dadad"+response.body().contentLength());
                final long total = response.body().contentLength();
                long sum = 0L;





                File file = new File(Environment.getExternalStorageDirectory(),"1111.jpg");

                FileOutputStream fos = new FileOutputStream(file);
                //不能有两个？？
                /*final Bitmap bitmap = BitmapFactory.decodeStream(is);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        imageView.setImageBitmap(bitmap);
                    }
                });*/

                int len = 0;
                byte[] buf = new byte[128];
                while ((len = is.read(buf)) != -1){
                  //  Log.i("mzym","?");
                    fos.write(buf,0,len);
                    sum += len;
                    Log.e("mzy","进度 = "+sum + "/"+total);
                    final long finalSum = sum;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(finalSum +"/"+total);
                        }
                    });
                }

                //需要try
                try {
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("mzy","downLoad success");
            }
        });
    }


    /**
     * 1.拿到okHttpClient对象
     * 2.构造Request
     * 2.1构造RequestBody
     * 2.2包装RequestBody
     * 3.Call执行
     */

}
