package com.example.omar.snapsearch.utils;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by omar on 21/03/18.
 */

public class VisionUtils {

    public final static String VISION_URL_PROTOCOL = "https://";
    public final static String VISION_URL_DOMAIN = ".api.cognitive.microsoft.com/vision/v1.0/analyze";

    public final static String VISION_HEADER_NAME_API_KEY = "Ocp-Apim-Subscription-Key";
    public final static String VISION_HEADER_NAME_CONTENT_TYPE = "Content-Type";

    public final static String VISION_HEADER_VALUE_API_KEY = "73ab3ec591a44434a058351005bf1bfe"; // Change for different API key.
    public final static String VISION_HEADER_VALUE_CONTENT_TYPE = "application/json";
    public final static String VISION_LOCATION_VALUE = "westcentralus"; // Change for different location.

    public final static String VISION_PARAM_NAME_VISUAL_FEATURES = "visualFeatures";
    public final static String VISION_PARAM_VALUE_VISUAL_FEATURES =
            "Categories,Tags,Description,Faces,Color,Adult"; // Change to tailor response data.
    public final static String VISION_PARAM_NAME_DETAILS = "details";
    public final static String VISION_PARAM_VALUE_DETAILS =
            "Celebrities,Landmarks";
    public final static String VISION_PARAM_NAME_LANGUAGE = "language";
    public final static String VISION_PARAM_VALUE_LANGUAGE =
            "en";

    public static class ImageResult implements Serializable{
        public String Name;
        public String Blob;
    }

    public static String buildVisionURL() {
        String baseURLString = VISION_URL_PROTOCOL + VISION_LOCATION_VALUE + VISION_URL_DOMAIN;

        Uri.Builder builder = Uri.parse(baseURLString).buildUpon();
        builder.appendQueryParameter(VISION_PARAM_NAME_VISUAL_FEATURES, VISION_PARAM_VALUE_VISUAL_FEATURES)
                .appendQueryParameter(VISION_PARAM_NAME_DETAILS, VISION_PARAM_VALUE_DETAILS)
                .appendQueryParameter(VISION_PARAM_NAME_LANGUAGE, VISION_PARAM_VALUE_LANGUAGE);
        return builder.build().toString();
    }

    public static ArrayList<String> parseResultsJSON(String resultsJSON) {
        try {
            JSONObject resultsObj = new JSONObject(resultsJSON);

            JSONObject resultsDescObj = resultsObj.getJSONObject("description");
            JSONArray resultsDescTags = resultsDescObj.getJSONArray("tags");
//            JSONArray resultsDescCaptions = resultsObj.getJSONArray("captions");

            ArrayList<String> resultsList = new ArrayList<String>();
            for (int i = 0; i < resultsDescTags.length(); i++) {
                if (i == 10) {
                    break;
                }
                String resultsTag = resultsDescTags.getString(i);
                resultsList.add(resultsTag);
            }
            Log.d("lol", resultsList.toString());
            return resultsList;
        } catch (JSONException e) {
            return null;
        }
    }

}
