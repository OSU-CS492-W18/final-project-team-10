package com.example.omar.snapsearch.utils;

import android.util.Log;

import com.example.omar.snapsearch.SingleImageActivity;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by omar on 21/03/18.
 */

public class NetworkUtils {

    private static final OkHttpClient mHTTPClient = new OkHttpClient();

    public static String doHTTPPost(byte[] imageByteArray) throws IOException {

        String url = VisionUtils.buildVisionURL();

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("", "",
                        RequestBody.create(MediaType.parse("image/*jpg"), imageByteArray))
                .build();

        Request req = new Request.Builder()
                .url(url)
                .addHeader(VisionUtils.VISION_HEADER_NAME_CONTENT_TYPE, VisionUtils.VISION_HEADER_VALUE_CONTENT_TYPE)
                .addHeader(VisionUtils.VISION_HEADER_NAME_API_KEY, VisionUtils.VISION_HEADER_VALUE_API_KEY)
                .post(body)
                .build();

        Response res = mHTTPClient.newCall(req).execute();
        try {
            return res.body().string();
        } finally {
            res.close();
        }
    }

}
