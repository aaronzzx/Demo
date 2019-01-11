package com.aaron.demo.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class ShaderTextView extends TextView {

    private static final String TAG = "ShaderTextView";
    private int mSizeChanged = 0;
    private int mOnDraw = 0;
    private int mViewWidth;
    private int mViewHeight;
    private int mTranslate;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;

    public ShaderTextView(Context context) {
        super(context);
    }

    public ShaderTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShaderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.d(TAG, "onDraw: start execute---" + mOnDraw++);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
//            Log.d(TAG, "mTranslate(1): " + mTranslate);
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
//                Log.d(TAG, "mTranslate(2): " + mTranslate);
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
//        Log.d(TAG, "onDraw: finish execute---" + mOnDraw++);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        Log.d(TAG, "onSizeChanged: start execute---" + mSizeChanged++);
        Log.d(TAG, "mViewWidth(1): " + mViewWidth);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            mViewHeight = getMeasuredHeight();
            Log.d(TAG, "mViewWidth(2): " + mViewWidth);
        }
        if (mViewWidth > 0) {
            Paint paint = getPaint();
            mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                    new int[]{Color.BLUE, Color.WHITE, Color.BLUE}, null,
                    Shader.TileMode.MIRROR);
            paint.setShader(mLinearGradient);
            mGradientMatrix = new Matrix();
        }
//        Log.d(TAG, "onSizeChanged: finish execute---" + mSizeChanged++);
    }
}
