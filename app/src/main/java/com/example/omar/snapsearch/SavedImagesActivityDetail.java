package com.example.omar.snapsearch;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.example.omar.snapsearch.utils.VisionUtils;

public class SavedImagesActivityDetail extends AppCompatActivity {

    private TextView mTVImageResultBlob;
    private ImageView mIVImageResultSaved;

    private boolean mIsSaved = false;

    private VisionUtils.ImageResult mImageResult;

    private SQLiteDatabase mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_images);

        mTVImageResultBlob = findViewById(R.id.tv_image_result_blob);

        SavedImageActivityDB dbHelper = new SavedImageActivityDB(this);

        mDB = dbHelper.getWritableDatabase();

        /*mIVImageResultSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsSaved = !mIsSaved;
                if (mIsSaved) {
                    addImageToDB();
                }
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        mDB.close();
        super.onDestroy();
    }

    private long addImageToDB(){
        if(mImageResult != null) {
            ContentValues cv = new ContentValues();
            cv.put(SavedImageAcivityContract.SavedImage.COLUMN_IMAGE_BLOB, mImageResult.Blob);
            return mDB.insert(SavedImageAcivityContract.SavedImage.TABLE_NAME, null, cv);
        }else{
            return -1;
        }
    }



}
