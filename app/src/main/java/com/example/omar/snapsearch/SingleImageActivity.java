package com.example.omar.snapsearch;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.omar.snapsearch.utils.NetworkUtils;
import com.example.omar.snapsearch.utils.VisionUtils;

import java.util.ArrayList;

public class SingleImageActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private final static int VISION_LOADER_ID = 0;

    private ImageView mImageView;
    private TextView mLoadingErrorMessage;
    private ProgressBar mLoadingProgressBar;
    private LinearLayout mLinearLayout;

    public byte[] mImageByteArray;

    private static int buttonCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        mImageView = findViewById(R.id.iv_image);
        mLoadingErrorMessage = findViewById(R.id.tv_loading_error);
        mLoadingProgressBar = findViewById(R.id.pb_loading_indicator);
        mLinearLayout = findViewById(R.id.ll_linear_layout);

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
        Log.d("lol", "Got results from loader.");
        if (data != null) {
//           TODO: Code for parsing and displaying the data goes here.
            ArrayList<String> resultsList = VisionUtils.parseResultsJSON(data);
            generateButtons(resultsList);
            mLoadingErrorMessage.setVisibility(View.INVISIBLE);
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
            mLinearLayout.setVisibility(View.VISIBLE);
        } else {
            mLinearLayout.setVisibility(View.INVISIBLE);
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
            mLoadingErrorMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    public void generateButtons(final ArrayList<String> resultsList) {
        for (int i = 0; i < resultsList.size(); i++) {
            final String query = resultsList.get(i);
            Button resultButton = new Button(this);
            resultButton.setLayoutParams(new LinearLayout.LayoutParams(600, 160));
            resultButton.setText(query);
            resultButton.setId(i);
            resultButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dispatchTakePictureIntent(query);
                }
            });
            mLinearLayout.addView(resultButton);
        }
    }

    private void dispatchTakePictureIntent(String query) {
        Intent webSearchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        webSearchIntent.putExtra(SearchManager.QUERY, query);
        startActivity(webSearchIntent);
    }
}
