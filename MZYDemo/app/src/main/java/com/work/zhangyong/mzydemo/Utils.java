package com.work.zhangyong.mzydemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.IOException;

/**
 * Created by zhangyong on 2017/2/27.
 */

public class Utils {

    /**
     * 压缩
     *
     * @param path 图片路径
     * @param w    压缩后的宽度 为null时表示限高
     * @param h    压缩后的高度 为null时表示限宽
     * @return
     */
    public static Bitmap resizeBitmap(String path, Integer w, Integer h) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //Bitmap bmp = BitmapFactory.decodeFile(path, options); //此时返回bm为空
        BitmapFactory.decodeFile(path, options);
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;
        if (null != w && null != h) {
            if (options.outWidth > w || options.outHeight > h) {
                final int wRatio = (int) Math.floor(options.outWidth / w);
                final int hRatio = (int) Math.floor(options.outHeight / h);
                be = hRatio > wRatio ? hRatio : wRatio;
            }
        } else {
            if (null != h && options.outHeight > h) {
                be = (int) Math.ceil(options.outHeight / h);
            } else if (null != w && options.outWidth > w) {
                be = (int) Math.ceil(options.outWidth / w);
            }
        }
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;
        options.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(path, options);//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        int degree = getBitmapDegree(path);
        if (degree != 0) {
            bmp = rotateBitmapByDegree(bmp, degree);
        }
        return bmp;
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵

        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

}
