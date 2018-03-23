package com.example.omar.snapsearch;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.example.omar.snapsearch.utils.VisionUtils;

public class SavedImagesActivity extends AppCompatActivity {

    private TextView mTVImageResultName;
    private TextView getmTVImageResultBlob;
    private ImageView mIVImageResultSaved;

    private boolean mIsSaved = false;

    private VisionUtils.ImageResult mImageResult;

    private SQLiteDatabase mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_images);

        mTVImageResultName = findViewById(R.id.tv_image_result_name);
        getmTVImageResultBlob = findViewById(R.id.tv_image_result_blob);

        SavedImageActivityDB dbHelper = new SavedImageActivityDB(this);

        mDB = dbHelper.getWritableDatabase();

        mIVImageResultSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsSaved = !mIsSaved;
                if (mIsSaved) {
                    addImageToDB();
                } else {
                    deleteImageFromDB();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        mDB.close();
        super.onDestroy();
    }

    private long addImageToDB(){
        if(mImageResult != null) {
            ContentValues row = new ContentValues();
            row.put(SavedImageAcivityContract.SavedImage.COLUMN_IMAGE_NAME, mImageResult.Name);
            row.put(SavedImageAcivityContract.SavedImage.COLUMN_IMAGE_BLOB, mImageResult.Blob);
            return mDB.insert(SavedImageAcivityContract.SavedImage.TABLE_NAME, null, row);
        }else{
            return -1;
        }
    }

    private void deleteImageFromDB() {
        if (mImageResult != null) {
            String sqlSelection = SavedImageAcivityContract.SavedImage.COLUMN_IMAGE_NAME + " = ?";
            String[] sqlSelectionArgs = { mImageResult.Name };
            mDB.delete(SavedImageAcivityContract.SavedImage.TABLE_NAME, sqlSelection, sqlSelectionArgs);
        }
    }
}
