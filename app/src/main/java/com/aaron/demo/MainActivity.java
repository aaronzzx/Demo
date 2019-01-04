package com.aaron.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aaron.demo.database.DatabaseActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDatabase_btn;

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
        }
    }

    private void initView() {
        mDatabase_btn = findViewById(R.id.btn_database);
    }

    private void setClickListener() {
        mDatabase_btn.setOnClickListener(this);
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
