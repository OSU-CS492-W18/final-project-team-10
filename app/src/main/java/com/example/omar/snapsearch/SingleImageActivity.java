package com.example.omar.snapsearch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.omar.snapsearch.utils.NetworkUtils;
import com.example.omar.snapsearch.utils.VisionUtils;

public class SingleImageActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private final static int VISION_LOADER_ID = 0;

    private ImageView mImageView;
    private TextView mLoadingErrorMessage;
    private ProgressBar mLoadingProgressBar;

    public byte[] mImageByteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        mImageView = findViewById(R.id.iv_image);
        mLoadingErrorMessage = findViewById(R.id.tv_loading_error);
        mLoadingProgressBar = findViewById(R.id.pb_loading_indicator);

        Bundle extras = getIntent().getExtras();
        mImageByteArray = extras.getByteArray("image");
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(mImageByteArray, 0, mImageByteArray.length);
        mImageView.setImageBitmap(imageBitmap);

        getSupportLoaderManager().initLoader(VISION_LOADER_ID, null, this);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        return new VisionLoader(this, mImageByteArray);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
        Log.d("lol", "Got results from loader.");
        if (data != null) {
            mLoadingErrorMessage.setVisibility(View.INVISIBLE);
//           TODO: Code for parsing and displaying the data goes here.
        } else {
            mLoadingErrorMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
