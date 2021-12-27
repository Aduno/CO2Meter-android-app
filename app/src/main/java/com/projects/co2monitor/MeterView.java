package com.projects.co2monitor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MeterView extends View {
    private Rect mMeter;
    private Paint mPaint;
    private int x;
    private int y;
    private int mCircleRadius;
    private int ppm;

    public MeterView(Context context) {
        super(context);
        init(null);
    }

    public MeterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MeterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MeterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setFakeBoldText(true);
        mPaint.setTextSize(70);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mPaint.setColor(Color.GREEN);
//        assert set != null;
//        ppm = set.getAttributeIntValue(0,-1);
//        if(ppm>2000){
//            changeColor(Color.RED);
//        }else if(ppm>1000){
//            changeColor(Color.rgb(255,153,51));
//        }else if(ppm>250){
//            changeColor(Color.rgb(0,204,0));
//        }else{
//            changeColor(Color.BLACK);
//        }
    }

    @Override
    public void onDraw(Canvas canvas){
        x = getWidth()/2;
        y = getHeight()/2;
        mCircleRadius = x/2;
        canvas.drawCircle(x,y,mCircleRadius,mPaint);
        changeColor(Color.BLACK);
        enableStroke(false);
        //Change string out with variable
        canvas.drawText("200PPM",x,(float)(y+y*0.04),mPaint);
    }
    public void enableStroke(boolean val){
        if(!val) mPaint.setStyle(Paint.Style.FILL);
        else mPaint.setStyle(Paint.Style.STROKE);
    }
    public void changeColor(int rgbVal){
        mPaint.setColor(rgbVal);
    }
    public void updateMeter(int ppm){
        this.ppm = ppm;
        invalidate();
    }
}
