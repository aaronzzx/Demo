package com.aaron.demo.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;

import com.aaron.demo.R;

public class Topbar extends ViewGroup {

    private String mTitleText;
    private float mTitleTextSize;
    private int mTitleTextColor;

    private Button mLeftButton;
    private String mLeftText;
    private int mLeftTextColor;
    private Drawable mLeftBackground;

    private Button mRightButton;
    private String mRightText;
    private int mRightTextColor;
    private Drawable mRightBackground;

    public Topbar(Context context) {
        super(context);
    }

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initView(Context context, AttributeSet attrs) {
        // TopBar's title
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        mTitleText = typedArray.getString(R.styleable.Topbar_titleText);
        mTitleTextSize = typedArray.getDimension(R.styleable.Topbar_titleTextSize, 18);
        mTitleTextColor = typedArray.getColor(R.styleable.Topbar_titleTextColor, 0xFFFFFFFF);

        // TopBar's leftButton
        mLeftButton = new Button(context);
        mLeftText = typedArray.getString(R.styleable.Topbar_leftText);
        mLeftTextColor = typedArray.getColor(R.styleable.Topbar_leftTextColor, 0xFFFFFFFF);
        mLeftBackground = typedArray.getDrawable(R.styleable.Topbar_leftBackground);

        // TopBar's rightButton
        mRightButton = new Button(context);
        mRightText = typedArray.getString(R.styleable.Topbar_rightText);
        mRightTextColor = typedArray.getColor(R.styleable.Topbar_rightTextColor, 0xFFFFFFFF);
        mRightBackground = typedArray.getDrawable(R.styleable.Topbar_rightBackground);

        // 释放资源
        typedArray.recycle();
    }
}
