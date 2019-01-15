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

    private Paint mShaderPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mViewWidth;
    private int mTranslate;

    public ShaderTextView(Context context) {
        super(context);
    }

    public ShaderTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mShaderPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.RED, 0xffffffff, Color.RED}, null,
                        Shader.TileMode.CLAMP);
                mShaderPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > mViewWidth * 2) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }
}
