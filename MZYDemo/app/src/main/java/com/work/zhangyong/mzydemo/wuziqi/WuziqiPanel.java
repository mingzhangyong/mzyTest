package com.work.zhangyong.mzydemo.wuziqi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.work.zhangyong.mzydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17-2-10.
 */

public class WuziqiPanel extends View {

    public interface OnChanged{
        void onChanged(List<Point> whiteArray, List<Point> blackArray);
    }


    private int mPanelWidth;
    private float mLineHeight ;
    private int MAX_LINE = 10;

    private Paint mPaint = new Paint();

    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;

    //白旗先手或者轮到白旗
    private boolean mIsWhite = true;
    private List<Point> mWhiteArray = new ArrayList<>();
    private List<Point> mBlackArray = new ArrayList<>();

    private float pieceRatio = 3*1.0f / 4;

    private boolean isGameOver;
    private boolean isWhiteWin;

    public WuziqiPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(0x44ff0000);
        init();
    }

    private void init() {
        mPaint.setColor(0x88000000);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mWhitePiece = BitmapFactory.decodeResource(getResources(),R.drawable.c);
        mBlackPiece = BitmapFactory.decodeResource(getResources(),R.drawable.b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize,heightSize);//设置正方形


        //防止滑动时出现问题
        if(widthMode == MeasureSpec.UNSPECIFIED){
            width = heightSize;
        }else if(heightMode == MeasureSpec.UNSPECIFIED){
            width = widthSize;
        }

        setMeasuredDimension(width,width);
    }

    //尺寸改变时
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = w;
        mLineHeight = mPanelWidth *1.0f/MAX_LINE;

        int pieceWidth = (int) (mLineHeight*pieceRatio);
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece,pieceWidth,pieceWidth,false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece,pieceWidth,pieceWidth,false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);

        drawPieces(canvas);

        checkIfWIn();
    }

    public void checkIfWIn(OnChanged onChanged) {
        onChanged.onChanged(mWhiteArray,mBlackArray);
         if(checkWhiteVertical(mWhiteArray)){
             isGameOver = true;
             Toast.makeText(getContext(),"游戏结束",Toast.LENGTH_LONG).show();
         }
    }
    public void checkIfWIn() {
        if(checkWhiteVertical(mWhiteArray) || checkWhiteHorizontal(mWhiteArray) || checkWhiteXie1(mWhiteArray) || checkWhiteXie2(mWhiteArray)){
            isGameOver = true;
            Toast.makeText(getContext(),"小呆胜利！",Toast.LENGTH_LONG).show();
        }
        if(checkWhiteVertical(mBlackArray) || checkWhiteHorizontal(mBlackArray) || checkWhiteXie1(mBlackArray) || checkWhiteXie2(mBlackArray)){
            isGameOver = true;
            Toast.makeText(getContext(),"小萌胜利！",Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkWhiteVertical(List<Point> points) {
        for (int i = 0; i <points.size() ; i++) {
            Point p = points.get(i);
            int x = p.x;
            int y = p.y;

            int count = 1;
            for (int j = 1; j <5 ; j++) {
                if(points.contains(new Point(x,y+j))){
                    count++;
                }else{
                    break;
                }
            }

            for (int j = 1; j <5 ; j++) {
                if(points.contains(new Point(x,y-j))){
                    count++;
                }else{
                    break;
                }
            }

            if(count == 5) {
                Log.i("mzy","win ?");
                return true;
            }
        }
        return false;
    }

    private boolean checkWhiteHorizontal(List<Point> points) {
        for (int i = 0; i <points.size() ; i++) {
            Point p = points.get(i);
            int x = p.x;
            int y = p.y;

            int count = 1;
            for (int j = 1; j <5 ; j++) {
                if(points.contains(new Point(x+j,y))){
                    count++;
                }else{
                    break;
                }
            }

            for (int j = 1; j <5 ; j++) {
                if(points.contains(new Point(x-j,y))){
                    count++;
                }else{
                    break;
                }
            }

            if(count == 5) {
                Log.i("mzy","win ?");
                return true;
            }
        }
        return false;
    }

    private boolean checkWhiteXie1(List<Point> points) {
        for (int i = 0; i <points.size() ; i++) {
            Point p = points.get(i);
            int x = p.x;
            int y = p.y;

            int count = 1;
            for (int j = 1; j <5 ; j++) {
                if(points.contains(new Point(x+j,y-j))){
                    count++;
                }else{
                    break;
                }
            }

            for (int j = 1; j <5 ; j++) {
                if(points.contains(new Point(x-j,y+j))){
                    count++;
                }else{
                    break;
                }
            }

            if(count == 5) {
                Log.i("mzy","win ?");
                return true;
            }
        }
        return false;
    }

    private boolean checkWhiteXie2(List<Point> points) {
        for (int i = 0; i <points.size() ; i++) {
            Point p = points.get(i);
            int x = p.x;
            int y = p.y;

            int count = 1;
            for (int j = 1; j <5 ; j++) {
                if(points.contains(new Point(x+j,y+j))){
                    count++;
                }else{
                    break;
                }
            }

            for (int j = 1; j <5 ; j++) {
                if(points.contains(new Point(x-j,y-j))){
                    count++;
                }else{
                    break;
                }
            }

            if(count == 5) {
                Log.i("mzy","win ?");
                return true;
            }
        }
        return false;
    }

    private void drawPieces(Canvas canvas) {

        Log.i("mzy","drawPieces  ");
        for (int i = 0,n = mWhiteArray.size();i <n ; i++) {
            Point whitePoint = mWhiteArray.get(i);
            canvas.drawBitmap(mWhitePiece,
                    (whitePoint.x+(1-pieceRatio)/2)*mLineHeight,
                    (whitePoint.y+(1-pieceRatio)/2)*mLineHeight,
                    null);
        }

        for (int i = 0,n = mBlackArray.size();i <n ; i++) {
            Point blackPoint = mBlackArray.get(i);
            canvas.drawBitmap(mBlackPiece,
                    (blackPoint.x+(1-pieceRatio)/2)*mLineHeight,
                    (blackPoint.y+(1-pieceRatio)/2)*mLineHeight,
                    null);
        }
    }

    private void drawBoard(Canvas canvas) {
        int w = mPanelWidth;
        float lineHeight = mLineHeight;   //使用局部变量替代全局变量 ，避免内存消耗？？？

        for (int i = 0; i <MAX_LINE ; i++) {
            int xStart = (int) (lineHeight/2);
            int xEnd = (int) (w - lineHeight/2);
            int y = (int) (lineHeight/2 +lineHeight*i);
            canvas.drawLine(xStart,y,xEnd,y,mPaint);
            canvas.drawLine(y,xStart,y,xEnd,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (isGameOver) return false;
        if(event.getAction() == MotionEvent.ACTION_UP){
            int x = (int) event.getX();
            int y = (int) event.getY();

            Log.i("mzy","x = "+x+"  y = "+y +mIsWhite);
            Point p = getValidPoint(x,y);

            if(mWhiteArray.contains(p) || mBlackArray.contains(p)){
                return false;
            }

            if(mIsWhite){
                mWhiteArray.add(p);
            }else{
                Log.i("mzy","black add ");
                mBlackArray.add(p);
            }
            invalidate();
            mIsWhite = !mIsWhite;

            return true;
        }
        return true;
    }

    private Point getValidPoint(int x, int y) {
        return new Point((int)(x/mLineHeight),(int)(y/mLineHeight));
    }
}
