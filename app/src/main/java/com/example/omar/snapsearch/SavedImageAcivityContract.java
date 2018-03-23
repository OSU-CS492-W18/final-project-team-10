package com.example.omar.snapsearch;

import android.provider.BaseColumns;

/**
 * Created by Kenon on 3/22/18.
 */

public class SavedImageAcivityContract {
    private SavedImageAcivityContract() {}
    public static class SavedImage implements BaseColumns{
        final static String TABLE_NAME = "Saved Image";
        final static String COLUMN_IMAGE_BLOB = "image";
        final static String COLUMN_TIMESTAMP = "timestamp";
    }
}
