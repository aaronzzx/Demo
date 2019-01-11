package com.aaron.demo.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aaron.demo.R;

public class TopBar extends RelativeLayout {

    private static final String TAG = "TopBar";

    private TypedArray mTypedArray;

    private String mTitle;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mLeftText;
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mRightText;
    private int mRightTextColor;
    private Drawable mRightBackground;

    private TextView mTitleView;
    private Button mLeftButton;
    private Button mRightButton;

    private RelativeLayout.LayoutParams mTitleParams;
    private RelativeLayout.LayoutParams mLeftParams;
    private RelativeLayout.LayoutParams mRightParams;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initValue(context, attrs);
        initView(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValue(context, attrs);
        initView(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initValue(context, attrs);
        initView(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void initView(Context context) {
        mTitleView = new TextView(context);
        mLeftButton = new Button(context);
        mRightButton = new Button(context);

        // 为创建的组件元素赋值，
        // 值来源于在引用的 xml 文件中给对应属性的赋值

        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        Log.d(TAG, "mLeftText: " + mLeftText);
        Log.d(TAG, "mLeftTextColor: " + mLeftTextColor);

        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        Log.d(TAG, "mRightText: " + mRightText);
        Log.d(TAG, "mRightTextColor: " + mRightTextColor);

        mTitleView.setText(mTitle);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setGravity(Gravity.CENTER);
        Log.d(TAG, "mTitle: " + mTitle);
        Log.d(TAG, "mTitleTextSize: " + mTitleTextSize);
        Log.d(TAG, "mTitleTextColor: " + mTitleTextColor);

        mLeftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        addView(mLeftButton, mLeftParams);

        mRightParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        addView(mTitleView, mTitleParams);
    }

    private void initValue(Context context, AttributeSet attrs) {
        // 通过这个函数，将在 attr.xml 中定义的 declare-styleable
        // 的属性的值存储到 TypedArray 中
        if (mTypedArray == null) {
            mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        }

        // 从 TypedArray 中取出对应的值来为要设置的属性赋值
        mTitle = mTypedArray.getString(R.styleable.TopBar_title);
        mTitleTextSize = mTypedArray.getDimension(R.styleable.TopBar_titleTextSize, 20);
        mTitleTextColor = mTypedArray.getColor(R.styleable.TopBar_titleTextColor, Color.WHITE);

        mLeftText = mTypedArray.getString(R.styleable.TopBar_leftText);
        mLeftTextColor = mTypedArray.getColor(R.styleable.TopBar_leftTextColor, Color.WHITE);
        mLeftBackground = mTypedArray.getDrawable(R.styleable.TopBar_leftBackground);

        mRightText = mTypedArray.getString(R.styleable.TopBar_rightText);
        mRightTextColor = mTypedArray.getColor(R.styleable.TopBar_rightTextColor, Color.WHITE);
        mRightBackground = mTypedArray.getDrawable(R.styleable.TopBar_rightBackground);

        // 获取完 TypedArray 的值后，一般要调用
        // recycle() 函数避免重新创建的时候出现错误
        mTypedArray.recycle();
    }

    public void setButtonVisible(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(VISIBLE);
            } else {
                mRightButton.setVisibility(VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(GONE);
            } else {
                mRightButton.setVisibility(GONE);
            }
        }
    }

    public void setTopbarOnClickListener(OnClickListener listener) {
        mLeftButton.setOnClickListener(v -> listener.onLeftClick());
        mRightButton.setOnClickListener(v -> listener.onRightClick());
    }

    public interface OnClickListener {
        void onLeftClick();

        void onRightClick();
    }
}
