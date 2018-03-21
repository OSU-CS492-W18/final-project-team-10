package com.example.omar.snapsearch.utils;

import android.net.Uri;


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
            "Categories,Tags,Description,Faces,ImageType,Color,Adult"; // Change to tailor response data.
    public final static String VISION_PARAM_NAME_DETAILS = "details";
    public final static String VISION_PARAM_VALUE_DETAILS =
            "Celebrities,Landmarks";
    public final static String VISION_PARAM_NAME_LANGUAGE = "language";
    public final static String VISION_PARAM_VALUE_LANGUAGE =
            "en";

    public static String buildVisionURL() {
        String baseURLString = VISION_URL_PROTOCOL + VISION_LOCATION_VALUE + VISION_URL_DOMAIN;

        Uri.Builder builder = Uri.parse(baseURLString).buildUpon();
        builder.appendQueryParameter(VISION_PARAM_NAME_VISUAL_FEATURES, VISION_PARAM_VALUE_VISUAL_FEATURES)
                .appendQueryParameter(VISION_PARAM_NAME_DETAILS, VISION_PARAM_VALUE_DETAILS)
                .appendQueryParameter(VISION_PARAM_NAME_LANGUAGE, VISION_PARAM_VALUE_LANGUAGE);
        return builder.build().toString();
    }

//    TODO: Implement a method to parse JSON.
//    TODO: Possibly create a Serializable object to hold result data.

}
