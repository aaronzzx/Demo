package com.aaron.demo.motion_event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.aaron.demo.R;

/**
 * 学习 MotionEvent
 */
public class MotionEventActivity extends AppCompatActivity {

    private static final String TAG = "MotionEventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);
        View finalView = findViewById(R.id.view);
        finalView.setOnClickListener(v -> Toast.makeText(this, "View is clicked.",
                Toast.LENGTH_SHORT).show());
    }
}
