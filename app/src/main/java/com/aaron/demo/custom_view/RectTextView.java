package com.aaron.demo.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class RectTextView extends TextView {

    private static final String TAG = "RectTextView";
    private static final int CALC_WIDTH = 0;
    private static final int CALC_HEIGHT = 1;
    private Paint mPaintBackground;
    private Paint mPaintStroke;

    public RectTextView(Context context) {
        super(context);
    }

    public RectTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
        mPaintBackground = new Paint();
        mPaintStroke = new Paint();
        mPaintBackground.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        mPaintBackground.setStyle(Paint.Style.FILL);
        mPaintStroke.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaintStroke.setStyle(Paint.Style.STROKE);
        mPaintStroke.setStrokeWidth(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 将自己测量好的宽高传递进去
        setMeasuredDimension(measureValue(widthMeasureSpec, CALC_WIDTH),
                measureValue(heightMeasureSpec, CALC_HEIGHT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画背景
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaintBackground);
        // 画边框
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaintStroke);
        super.onDraw(canvas);
    }

    private int measureValue(int measureSpec, int type) {
        int result;
        // 获得测量模式与测量大小
        int measureMode = MeasureSpec.getMode(measureSpec);
        int measureSize = MeasureSpec.getSize(measureSpec);
        // 根据不同的测量模式处理测量大小
        if (measureMode == MeasureSpec.EXACTLY) {
            result = measureSize;
        } else {
            result = calculateDimen(type);
            if (measureMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, measureSize);
            }
        }
        return result;
    }

    private int calculateDimen(int type) {
        Paint paint = new Paint();
        // 将字体大小设置进去
        paint.setTextSize(getTextSize());
        // 获取字符串像素宽度
        float textWidth = paint.measureText(getText().toString());
        int result = 0;
        switch (type) {
            case CALC_WIDTH:
                // 为宽度增加间距
                result = (int) (textWidth + 80);
                break;
            case CALC_HEIGHT:
                // 为高度增加间距
                float textSize = getTextSize();
                result = (int) (textSize + 80);
                // 判断行数，不严谨
                if (textWidth >= 1080) {
                    int flag = (int) (textWidth / 1080);
                    result = (int) (textSize * flag + 180);
                }
                break;
        }
        return result;
    }
}
