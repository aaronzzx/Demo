package com.aaron.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aaron.demo.content_provider.ProviderActivity;
import com.aaron.demo.custom_view.MyCustomActivity;
import com.aaron.demo.database.DatabaseActivity;
import com.aaron.demo.motion_event.MotionEventActivity;
import com.aaron.demo.scroll.ScrollActivity;

/**
 * 程序入口
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDatabase_btn, mContentProvider, mCustomView, mMotionEvent, mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setClickListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_database: // 数据库示例
                startActivity(DatabaseActivity.class);
                break;
            case R.id.btn_content_provider: // 内容提供器示例
                startActivity(ProviderActivity.class);
                break;
            case R.id.btn_custom_view: // 自定义View示例
                startActivity(MyCustomActivity.class);
                break;
            case R.id.btn_motion_event: // MotionEvent 示例
                startActivity(MotionEventActivity.class);
                break;
            case R.id.btn_scroll: // Scroll示例
                startActivity(ScrollActivity.class);
                break;
        }
    }

    private void initView() {
        mDatabase_btn = findViewById(R.id.btn_database);
        mContentProvider = findViewById(R.id.btn_content_provider);
        mCustomView = findViewById(R.id.btn_custom_view);
        mMotionEvent = findViewById(R.id.btn_motion_event);
        mScrollView = findViewById(R.id.btn_scroll);
    }

    private void setClickListener() {
        mDatabase_btn.setOnClickListener(this);
        mContentProvider.setOnClickListener(this);
        mCustomView.setOnClickListener(this);
        mMotionEvent.setOnClickListener(this);
        mScrollView.setOnClickListener(this);
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
