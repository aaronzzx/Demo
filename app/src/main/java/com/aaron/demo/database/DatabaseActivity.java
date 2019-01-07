package com.aaron.demo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aaron.demo.R;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    // 控件
    private EditText mInput_edit;
    private TextView mDisplay_text;
    private Button mInsert_btn, mQuery_btn, mClear_btn;
    // 事务
    private SQLiteDatabase mDatabase;
    private ContentValues mContentValues;
    private DatabaseUser mDatabaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        initTransaction(); // 初始化数据库事务
        initView(); // 初始化控件
        setClickListener(); // 设置点击监听器
    }

    @Override
    public void onClick(View v) {
        String args = mInput_edit.getText().toString();
        switch (v.getId()) {
            case R.id.btn_insert: // 组装数据
                try {
                    mDatabaseUser.insertData(args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_query: // 查询数据
                mDatabaseUser.queryData(args);
                break;
            case R.id.btn_clear: // 清除数据
                mDatabaseUser.clearData(args);
                break;
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mInput_edit = findViewById(R.id.edit_text);
        mDisplay_text = findViewById(R.id.text_view);
        mInsert_btn = findViewById(R.id.btn_insert);
        mQuery_btn = findViewById(R.id.btn_query);
        mClear_btn = findViewById(R.id.btn_clear);
    }

    /**
     * 设置点击监听器
     */
    private void setClickListener() {
        mInsert_btn.setOnClickListener(this);
        mQuery_btn.setOnClickListener(this);
        mClear_btn.setOnClickListener(this);
    }

    /**
     * 初始化数据库事务
     */
    private void initTransaction() {
        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this,
                "Note.db", null, 1);
        mDatabase = databaseHelper.getWritableDatabase();
        mContentValues = new ContentValues();
        mDatabaseUser = new DatabaseUser();
    }

    /**
     * 格式化日期
     */
    @SuppressLint("SimpleDateFormat")
    private String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 - HH:mm");
        return format.format(date);
    }

    private class DatabaseUser {

        private void insertData(String args) throws IOException {
            if (TextUtils.isEmpty(args)) {
                Toast.makeText(DatabaseActivity.this, "请先输入",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (new ByteArrayInputStream(args.getBytes())));
            String title = reader.readLine();
            mContentValues.put("title", title); // 标题
            mContentValues.put("content", args); // 内容
            mContentValues.put("date", formatDate(new Date())); // 日期
            mDatabase.insert("Note", null, mContentValues);
            mContentValues.clear();
            Toast.makeText(DatabaseActivity.this, "插入成功",
                    Toast.LENGTH_SHORT).show();
        }

        private void queryData(String args) {
            Cursor cursor;
            if (!TextUtils.isEmpty(args)) {
                cursor = mDatabase.query("Note", null, "title = ?",
                        new String[]{args}, null, null, null);
            } else {
                cursor = mDatabase.query("Note", null, null,
                        null, null, null, null);
            }
            StringBuilder text = new StringBuilder();
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                if (!TextUtils.isEmpty(title)) {
                    text.append(title);
                }
                if (!TextUtils.isEmpty(content)) {
                    text.append("\n\n").append(content);
                }
                if (!TextUtils.isEmpty(date)) {
                    text.append("\n\n").append(date).append("\n-----------------------------\n\n");
                }
            }
            String data = text.toString();
            if (!TextUtils.isEmpty(data)) {
                mDisplay_text.setText(data);
                Toast.makeText(DatabaseActivity.this, "查询成功",
                        Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }

        private void clearData(String args) {
            if (!TextUtils.isEmpty(args)) {
                mDatabase.delete("Note", "title = ?", new String[]{args});
                Toast.makeText(DatabaseActivity.this, "数据清除成功",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DatabaseActivity.this, "数据全部清除成功",
                        Toast.LENGTH_SHORT).show();
                mDatabase.delete("Note", null, null);
            }
        }
    }
}
