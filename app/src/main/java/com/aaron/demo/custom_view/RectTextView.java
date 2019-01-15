package com.aaron.demo.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class RectTextView extends TextView {

    private Paint mPaintBackground;
    private Paint mPaintStroke;

    public RectTextView(Context context) {
        super(context);
    }

    public RectTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintBackground = new Paint();
        mPaintStroke = new Paint();
        mPaintBackground.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        mPaintBackground.setStyle(Paint.Style.FILL);
        mPaintStroke.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaintStroke.setStyle(Paint.Style.STROKE);
        mPaintStroke.setStrokeWidth(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaintBackground);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10,
                getMeasuredHeight() - 10, mPaintStroke);
        super.onDraw(canvas);
    }
}
