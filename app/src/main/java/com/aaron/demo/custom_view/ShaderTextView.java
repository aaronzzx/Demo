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

    private static final String TAG = "ShaderTextView";

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
            // 如果控件宽度为 0 ，那么就获取已测量的宽度并赋值
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                // 当控件宽度大于 0 时才允许初始化 Paint,Shader,Matrix
                // 获取 TextView 的 TextPaint
                mShaderPaint = getPaint();
                // 初始化渐变规格
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.RED, 0xffffffff, Color.RED}, null,
                        Shader.TileMode.CLAMP);
                // 为画笔设置渐变属性
                mShaderPaint.setShader(mLinearGradient);
                // 初始化 Matrix
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            // 不停改变渐变位置，每次增加的量为控件宽度的五分之一
            mTranslate += mViewWidth / 5;
            if (mTranslate > mViewWidth * 2) {
                // 当渐变位置超过控件宽度的两倍时，使用控件宽度负值重置渐变位置
                mTranslate = -mViewWidth;
            }
            // 为矩阵设置位移
            mGradientMatrix.setTranslate(mTranslate, 0);
            // 为 Shader 设置渐变效果的位移矩阵
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            // 每 0.1 秒通知系统重绘，达到闪烁效果
            postInvalidateDelayed(100);
        }
    }
}
