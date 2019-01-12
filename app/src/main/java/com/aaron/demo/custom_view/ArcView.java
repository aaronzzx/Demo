package com.aaron.demo.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class ArcView extends View {

    private static final String TAG = "ArcView";

    private int mCircleXY;
    private float mRadius;
    private RectF mArcRectF;
    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;

    public ArcView(Context context) {
        super(context);
        initParams();
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultValue(widthMeasureSpec), getDefaultValue(heightMeasureSpec));
    }

    private int getDefaultValue(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 800;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleXY, mCircleXY - 100, mRadius, mCirclePaint);
        canvas.drawArc(mArcRectF, -90, 270, false, mArcPaint);
        String text = "Android";
        Log.d(TAG, "text.length(): " + text.length());
        canvas.drawText(text, 0, text.length(), mCircleXY - 140, mCircleXY - 50, mTextPaint);
    }

    private void initParams() {
        DisplayMetrics screen = getResources().getDisplayMetrics();
        int screenWidth = screen.widthPixels;
        mCircleXY = screenWidth / 2;
        mRadius = (float) (screenWidth / 5);
        mArcRectF = new RectF((float) (screenWidth * 0.2),
                (float) (screenWidth * 0.1),
                (float) (screenWidth * 0.8),
                (float) (screenWidth * 0.7));
        mCirclePaint = new Paint();
        mCirclePaint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        mCirclePaint.setStyle(Paint.Style.FILL);
        mArcPaint = new Paint();
        mArcPaint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        mArcPaint.setStrokeWidth(60);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mTextPaint = new Paint();
        mTextPaint.setTextSize(80);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setStyle(Paint.Style.FILL);
    }
}
