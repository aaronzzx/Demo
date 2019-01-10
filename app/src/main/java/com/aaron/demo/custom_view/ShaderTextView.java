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
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class ShaderTextView extends TextView {

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
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            mViewHeight = getMeasuredHeight();
        }
        if (mViewWidth > 0) {
            Paint paint = getPaint();
            mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                    new int[]{Color.RED, Color.WHITE, Color.BLUE}, new float[]{0.1F, 0.8F, 0.1F},
                    Shader.TileMode.CLAMP);
            paint.setShader(mLinearGradient);
            mGradientMatrix = new Matrix();
        }
    }
}
