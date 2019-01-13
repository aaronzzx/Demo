package com.aaron.demo.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DragView extends View {

    private int mLastX;
    private int mLastY;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // method 1
//        int x = (int) event.getX();
//        int y = (int) event.getY();
        // method 2
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // method 1
//                mLastX = x;
//                mLastY = y;

                // method 2
                mLastX = rawX;
                mLastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                // method 1
//                int offsetX = x - mLastX;
//                int offsetY = y - mLastY;
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);

                // method 2
                int offsetX = rawX - mLastX;
                int offsetY = rawY - mLastY;

                // method 3,同时对上下或者左右进行偏移
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                // method 4,通过 LayoutParams 进行偏移，前提是需要有父布局，不然
                // 无法获取 LayoutParams，并且需要根据父布局类型转换类型，
                // 如 LinearLayout 或 RelativeLayout
//                LinearLayout.LayoutParams layoutParams =
//                        (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                // method 5，通过 ViewGroup.MarginLayoutParams 实现更加方便，
                // 不必考虑父布局类型，和 method 4 的本质一样
//                ViewGroup.MarginLayoutParams layoutParams =
//                        (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                // method 6，scrollBy() 和 scrollTo()
//                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                ((View) getParent()).scrollTo(-offsetX, -offsetY);

                // 如果在执行完 ACTION_MOVE 后不重置初始坐标，会出现无法精准获取 View 偏移量
//                mLastX = rawX;
//                mLastY = rawY;
                break;
        }
        return true;
    }
}
