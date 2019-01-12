package com.aaron.demo.custom_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.aaron.demo.R;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        setTopbar();
        initView();
    }

    private void initView() {
        Button first = findViewById(R.id.btn_first);
        Button second = findViewById(R.id.btn_second);
        first.setOnClickListener(v -> {
            startActivity(new Intent(CustomViewActivity.this, CustomView2Activity.class));
        });
        second.setOnClickListener(v -> {
            startActivity(new Intent(CustomViewActivity.this, CustomView3Activity.class));
        });
    }

    private void setTopbar() {
        TopBar topBar = findViewById(R.id.top_bar);
        topBar.setTopbarOnClickListener(new TopBar.OnClickListener() {
            @Override
            public void onLeftClick() {
                topBar.setButtonVisible(true);
                Toast.makeText(CustomViewActivity.this, "Visible",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                topBar.setButtonVisible(false);
                Toast.makeText(CustomViewActivity.this, "Gone",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
