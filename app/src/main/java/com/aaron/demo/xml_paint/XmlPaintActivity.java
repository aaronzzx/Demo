package com.aaron.demo.xml_paint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.aaron.demo.R;

public class XmlPaintActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "XmlPaintActivity";
    private static final int MID_VALUE = 50;
    private ImageView mImageView;
    private Bitmap mBitmap;
    private float mHue = 1.0F;
    private float mSaturation = 1.0F;
    private float mLum = 1.0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_paint);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mImageView = findViewById(R.id.image_view);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_1);
        mImageView.setImageBitmap(mBitmap);

        SeekBar hueSeekBar = findViewById(R.id.seek_hue);
        SeekBar saturationSeekBar = findViewById(R.id.seek_saturation);
        SeekBar lumSeekBar = findViewById(R.id.seek_lum);

        hueSeekBar.setOnSeekBarChangeListener(this);
        saturationSeekBar.setOnSeekBarChangeListener(this);
        lumSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seek_hue:
                mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seek_saturation:
                mSaturation = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seek_lum:
                mLum = progress * 1.0F / MID_VALUE;
                break;
        }
//        mImageView.setImageBitmap(handleImageEffect(mBitmap, mHue, mSaturation, mLum));
    }

    private Bitmap handleImageEffect(Bitmap bitmap, float hue, float saturation, float lum) {
        Log.d(TAG, "hue: " + hue);
        Log.d(TAG, "saturation: " + saturation);
        Log.d(TAG, "lum: " + lum);
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.seek_hue:
                mHue = (seekBar.getProgress() - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seek_saturation:
                mSaturation = seekBar.getProgress() * 1.0F / MID_VALUE;
                break;
            case R.id.seek_lum:
                mLum = seekBar.getProgress() * 1.0F / MID_VALUE;
                break;
        }
        mImageView.setImageBitmap(handleImageEffect(mBitmap, mHue, mSaturation, mLum));
    }
}
