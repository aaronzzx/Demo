package com.aaron.demo.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MusicRect extends View {

    private static final String TAG = "MusicRect";

    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;
    private int mRectWidth;

    public MusicRect(Context context) {
        super(context);
    }

    public MusicRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getWidth();
        mViewHeight = getHeight();
        mRectWidth = (int) (mViewWidth * 0.6 / 15);
        LinearGradient gradient = new LinearGradient(0, 0, mRectWidth, mViewHeight,
                Color.RED, Color.BLACK, Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setShader(gradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 15; i++) {
            double random = Math.random();
            float left = (float) (mViewWidth * 0.4 / 2 + mRectWidth * i + 10);
            float currentHeight = (float) (mViewHeight * random);
            float right = (float) (mViewWidth * 0.4 / 2 + mRectWidth * (i + 1));
//            Log.d(TAG, "mWidth: " + mWidth * 0.4 / 2);
//            Log.d(TAG, "mViewHeight: " + mViewHeight);
            Log.d(TAG, "left: " + left);
            Log.d(TAG, "right: " + right);
//            Log.d(TAG, "top: " + currentHeight);
//            Log.d(TAG, "bottom: " + mViewHeight);
            canvas.drawRect(left, currentHeight, right, mViewHeight, mPaint);
        }
        postInvalidateDelayed(300);
    }
}
