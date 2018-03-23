package com.example.omar.snapsearch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kenon on 3/22/18.
 */

public class SavedImageActivityDB extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "SavedImage.db";
    private static int DATABASE_VERSION = 1;

    public SavedImageActivityDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        final String SQL_CREATE_SAVED_IMAGES_TABLE =
                "CREATE TABLE " + SavedImageAcivityContract.SavedImage.TABLE_NAME + "(" +
                        SavedImageAcivityContract.SavedImage._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        SavedImageAcivityContract.SavedImage.COLUMN_IMAGE_BLOB + " TEXT NOT NULL, " +
                        SavedImageAcivityContract.SavedImage.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";
        db.execSQL(SQL_CREATE_SAVED_IMAGES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + SavedImageAcivityContract.SavedImage.TABLE_NAME);
        onCreate(db);
    }

}
