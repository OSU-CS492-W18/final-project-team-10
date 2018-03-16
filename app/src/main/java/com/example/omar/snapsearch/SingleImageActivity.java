package com.example.omar.snapsearch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SingleImageActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        mImageView = findViewById(R.id.iv_image);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("image");
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        mImageView.setImageBitmap(imageBitmap);
    }
}
