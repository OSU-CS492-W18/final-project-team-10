package com.example.omar.snapsearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omar.snapsearch.utils.VisionUtils;

import java.util.ArrayList;

/**
 * Created by Kenon on 3/22/18.
 */

public class SnapSearchAdapter extends RecyclerView.Adapter<SnapSearchAdapter.ImageResultViewHolder> {
    private ArrayList<VisionUtils.ImageResult> mImageResultsList;
    onImageItemClickListener mImageItemClickListener;

    SnapSearchAdapter(onImageItemClickListener imageItemClickListener){
        mImageItemClickListener = imageItemClickListener;
    }

    public void updateImageResults(ArrayList<VisionUtils.ImageResult> imageResultsList){
        mImageResultsList = imageResultsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(mImageResultsList != null){
            return mImageResultsList.size();
        } else{
            return 0;
        }
    }

    @Override
    public ImageResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.image_result_item, parent, false);
        return new ImageResultViewHolder(view);
    }

    public interface onImageItemClickListener{
       void onImageItemClick(VisionUtils.ImageResult imageResult);
    }

    @Override
    public void onBindViewHolder(ImageResultViewHolder holder, int position) {
        holder.bind(mImageResultsList.get(position));
    }

    class ImageResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mImageResultTV;

        public ImageResultViewHolder(View itemView) {
            super(itemView);
            mImageResultTV = (TextView)itemView.findViewById(R.id.tv_image_result);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VisionUtils.ImageResult imageResult = mImageResultsList.get(getAdapterPosition());
                    mImageItemClickListener.onImageItemClick(imageResult);
                }
            });
        }

        public void bind(VisionUtils.ImageResult imageResult) {
            mImageResultTV.setText(imageResult.Blob);
        }
    }

}
