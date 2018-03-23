package com.example.omar.snapsearch;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.omar.snapsearch.utils.VisionUtils;

import java.util.ArrayList;

/**
 * Created by Kenon on 3/22/18.
 */

public class SavedImagesActivity extends AppCompatActivity implements SnapSearchAdapter.onImageItemClickListener {
    private RecyclerView mSavedImageResultsRV;
    private SnapSearchAdapter mAdapter;

    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_images_activity);

        mSavedImageResultsRV = findViewById(R.id.rv_saved_image_results);
        mSavedImageResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSavedImageResultsRV.setHasFixedSize(true);

        SavedImageActivityDB dbHelper = new SavedImageActivityDB(this);
        mDB = dbHelper.getReadableDatabase();

        mAdapter = new SnapSearchAdapter(this);
        mAdapter.updateImageResults(getAllSavedImagesFromDB());
        mSavedImageResultsRV.setAdapter(mAdapter);
    }

    @Override
    public void onImageItemClick(VisionUtils.ImageResult imageResult) {
        Intent detailedSearchResultIntent = new Intent(this, SavedImagesActivityDetail.class);
        startActivity(detailedSearchResultIntent);
    }

    @Override
    protected void onDestroy() {
        mDB.close();
        super.onDestroy();
    }

    private ArrayList<VisionUtils.ImageResult> getAllSavedImagesFromDB() {
        Cursor cursor = mDB.query(
                SavedImageAcivityContract.SavedImage.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                SavedImageAcivityContract.SavedImage.COLUMN_TIMESTAMP + " DESC"
        );

        ArrayList<VisionUtils.ImageResult> savedImageResults = new ArrayList<>();
        while (cursor.moveToNext()) {
            VisionUtils.ImageResult imageResult = new VisionUtils.ImageResult();
            imageResult.Blob = cursor.getString(
                    cursor.getColumnIndex(SavedImageAcivityContract.SavedImage.COLUMN_IMAGE_BLOB)
            );
            savedImageResults.add(imageResult);
        }
        cursor.close();
        return savedImageResults;
    }
}
