package com.aaron.demo.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aaron.demo.R;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        setTopbar();
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
