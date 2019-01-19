package com.aaron.demo.xml_paint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.aaron.demo.R;

public class XmlPaintActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "XmlPaintActivity";
    private static final int MID_VALUE = 50; // SeekBar 中间值
    private ImageView mImageView;
    private Bitmap mBitmap;
    private static final int[] mImgArray = {R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
            R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10,
            R.drawable.img_11, R.drawable.img_12, R.drawable.img_13};
    private SeekBar mHueSeekBar;
    private SeekBar mSaturationSeekBar;
    private SeekBar mLumSeekBar;
    private float mSaturation = 1.0F;
    private float mLum = 1.0F;
    private float mHue = 0;
    private int mImgSelect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_paint);
        initView();
    }

    /**
     * 透明状态栏，并使布局融入状态栏
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mImageView = findViewById(R.id.image_view);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_1);
        mImageView.setImageBitmap(mBitmap);

        mHueSeekBar = findViewById(R.id.seek_hue);
        mSaturationSeekBar = findViewById(R.id.seek_saturation);
        mLumSeekBar = findViewById(R.id.seek_lum);

        mHueSeekBar.setOnSeekBarChangeListener(this);
        mSaturationSeekBar.setOnSeekBarChangeListener(this);
        mLumSeekBar.setOnSeekBarChangeListener(this);
        mImageView.setOnClickListener(v -> {
            mBitmap = BitmapFactory.decodeResource(getResources(), getImgResource());
            mImageView.setImageBitmap(mBitmap);
            mHueSeekBar.setProgress(50);
            mSaturationSeekBar.setProgress(50);
            mLumSeekBar.setProgress(50);
        });
    }

    private int getImgResource() {
        if (mImgSelect == mImgArray.length - 1) {
            mImgSelect = -1;
        }
        mImgSelect++;
        return mImgArray[mImgSelect];
    }

    /**
     * 随着手指在 SeekBar 上移动而回调
     */
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

    /**
     * 图像处理
     *
     * @param bitmap     要修改的原图
     * @param hue        需要调整的色相的值
     * @param saturation 需要调整的饱和度的值
     * @param lum        需要调整的亮度的值
     * @return 由于 Android 不允许直接修改原图，因此需要返回一个已经修改的 Bitmap
     */
    private Bitmap handleImageEffect(Bitmap bitmap, float hue, float saturation, float lum) {
//        Log.d(TAG, "hue: " + hue);
//        Log.d(TAG, "saturation: " + saturation);
//        Log.d(TAG, "lum: " + lum);
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

    /**
     * 手指接触 SeekBar 时会回调
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 手指离开 SeekBar 时会回调
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = seekBar.getProgress();
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
        mImageView.setImageBitmap(handleImageEffect(mBitmap, mHue, mSaturation, mLum));
    }
}
