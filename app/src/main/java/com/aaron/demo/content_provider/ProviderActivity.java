package com.aaron.demo.content_provider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aaron.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 学习 ContentProvider
 */
public class ProviderActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;
    private ArrayAdapter mAdapter;
    private List<String> mContactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        initView();
        checkPermission();
    }

    private void initView() {
        ListView listView = findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mContactsList);
        listView.setAdapter(mAdapter);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(ProviderActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProviderActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION);
        } else {
            readContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(ProviderActivity.this, "请先打开权限",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract
                            .CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract
                            .CommonDataKinds.Phone.NUMBER));
                    mContactsList.add(displayName + "\n" + number);
                }
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
