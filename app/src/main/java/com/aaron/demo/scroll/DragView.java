package com.aaron.demo.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

public class DragView extends View {

    private static final String TAG = "DragView";
    private Scroller mScroller;
    private int mLastX;
    private int mLastY;

    public DragView(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断 Scroller 是否执行完毕
        if (mScroller.computeScrollOffset()) {
            // getCurrX() 和 getCurrY() 来获得当前的滑动坐标
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用 computeScroll，因为系在绘制 View 时会在 draw() 中调用
            // 该方法，实际上就是使用 scrollTo() 方法
            invalidate();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int scrollX = ((View) getParent()).getScrollX();
//        int scrollY = ((View) getParent()).getScrollY();

        // method 1
        int x = (int) event.getX();
        int y = (int) event.getY();
        // method 2
//        int rawX = (int) event.getRawX();
//        int rawY = (int) event.getRawY();
//        Log.d(TAG, "rawX: " + rawX);
//        Log.d(TAG, "rawY: " + rawY);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // method 1
                mLastX = x;
                mLastY = y;

                // method 2
//                mLastX = rawX;
//                mLastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                // method 1
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);

                // method 2
//                int offsetX = rawX - mLastX;
//                int offsetY = rawY - mLastY;

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
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
//                ((View) getParent()).scrollTo(scrollX - offsetX, scrollY - offsetY);

                // 如果在执行完 ACTION_MOVE 后不重置初始坐标，会出现无法精准获取 View 偏移量
//                mLastX = rawX;
//                mLastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(),
                        -viewGroup.getScrollX(), -viewGroup.getScrollY(), 1000);
                invalidate();
                break;
        }
        return true;
    }
}
