package com.example.omar.snapsearch;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.omar.snapsearch.utils.NetworkUtils;

import java.io.IOException;

/**
 * Created by omar on 21/03/18.
 */

public class VisionLoader extends AsyncTaskLoader<String> {

    private byte[] mImageByteArray;
    private String mResultsJSON;

    public VisionLoader(Context context, byte[] imageByteArray) {
        super(context);
        mImageByteArray= imageByteArray;
    }

    @Override
    protected void onStartLoading() {
        if (mResultsJSON != null) {
            Log.d("lol", "Loader returning cached results.");
            deliverResult(mResultsJSON);
        } else {
            forceLoad();
        }
    }

    @Override
    public String loadInBackground() {
        Log.d("lol", "Loading results from Microsoft Computer Vision API");
        String results = null;
        try {
            results = NetworkUtils.doHTTPPost(mImageByteArray);
            Log.d("lol", results);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void deliverResult(String data) {
        mResultsJSON = data;
        super.deliverResult(data);
    }
}
