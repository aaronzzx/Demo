package com.aaron.demo.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aaron.demo.R;

public class MyCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom);
    }
}
