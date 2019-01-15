package com.aaron.demo.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.aaron.demo.R;

public class Topbar extends RelativeLayout {

    private static final String TAG = "Topbar";
    private OnTopbarClickListener mListener;

    // TitleView
    private ShaderTextView mTitleView;
    private String mTitleText;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private LayoutParams mTitleParams;

    // LeftButton
    private Button mLeftButton;
    private String mLeftText;
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private LayoutParams mLeftParams;

    // RightButton
    private Button mRightButton;
    private String mRightText;
    private int mRightTextColor;
    private Drawable mRightBackground;
    private LayoutParams mRightParams;

    public Topbar(Context context) {
        super(context);
    }

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initView(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 初始化控件值与布局属性
     */
    private void initView(Context context) {
        // init view
        mTitleView = new ShaderTextView(context);
        mLeftButton = new Button(context);
        mRightButton = new Button(context);

        // set titleView's xml attr
        mTitleView.setText(mTitleText);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        // set leftButton's xml attr
        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);

        // set rightButton's xml attr
        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);

        // 设置控件布局属性
        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        // 添加到 ViewGroup
        addView(mTitleView, mTitleParams);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_START, TRUE);
        mLeftParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_END, TRUE);
        mRightParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        addView(mRightButton, mRightParams);
    }

    /**
     * 将 attrs 文件中声明的属性赋值给控件
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        // TopBar's attrs of title
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        mTitleText = typedArray.getString(R.styleable.Topbar_titleText);
        mTitleTextSize = typedArray.getDimension(R.styleable.Topbar_titleTextSize, 24);
        mTitleTextColor = typedArray.getColor(R.styleable.Topbar_titleTextColor, 0xFFFFFFFF);

        // TopBar's attrs of leftButton
        mLeftText = typedArray.getString(R.styleable.Topbar_leftText);
        mLeftTextColor = typedArray.getColor(R.styleable.Topbar_leftTextColor, 0xFFFFFFFF);
        mLeftBackground = typedArray.getDrawable(R.styleable.Topbar_leftBackground);

        // TopBar's attrs of rightButton
        mRightText = typedArray.getString(R.styleable.Topbar_rightText);
        mRightTextColor = typedArray.getColor(R.styleable.Topbar_rightTextColor, 0xFFFFFFFF);
        mRightBackground = typedArray.getDrawable(R.styleable.Topbar_rightBackground);

        // 释放资源
        typedArray.recycle();
    }

    public void hideTitleView() {
        mTitleView.setVisibility(GONE);
    }

    public void showTitleView() {
        mTitleView.setVisibility(VISIBLE);
    }

    public void setOnTopbarClickListener(OnTopbarClickListener listener) {
        if (mListener == null) {
            mListener = listener;
        }
        mLeftButton.setOnClickListener(v -> {
            mListener.leftClick("你点击了 LeftButton");
        });
        mRightButton.setOnClickListener(v -> {
            mListener.rightClick("你点击了 RightButton");
        });
    }

    public interface OnTopbarClickListener {

        void leftClick(String msg);

        void rightClick(String msg);
    }
}
