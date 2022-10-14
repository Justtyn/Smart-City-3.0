package com.example.smartcity30.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity30.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class GuidePageAdapter extends BannerAdapter<Integer, GuidePageAdapter.ImageHolder> {

    public GuidePageAdapter(List<Integer> data) {
        super(data);
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup viewGroup, int i) {

        ImageView banner_back_image = (ImageView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.banner_back_image, viewGroup, false);

        return new ImageHolder(banner_back_image);
    }

    @Override
    public void onBindView(ImageHolder imageHolder, Integer integer, int position, int size) {
        imageHolder.iv_banner_guide_back_image.setImageResource(mDatas.get(position));
    }

    static class ImageHolder extends RecyclerView.ViewHolder {

        ImageView iv_banner_guide_back_image;
        View itemView;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_banner_guide_back_image = itemView.findViewById(R.id.iv_banner_guide_back_image);

        }
    }
}
