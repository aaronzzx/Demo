package com.aaron.demo.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aaron.demo.R;

public class MyCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom);
        Topbar topBar = findViewById(R.id.top_bar);
        topBar.setOnTopbarClickListener(new Topbar.OnTopbarClickListener() {
            @Override
            public void leftClick(String msg) {
                topBar.showTitleView();
                Toast.makeText(MyCustomActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick(String msg) {
                topBar.hideTitleView();
                Toast.makeText(MyCustomActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
