package com.example.smartcity30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity30.R;
import com.example.smartcity30.bean.NewsDetailsResult;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class NewsFragmentBannerAdapter extends BannerAdapter<String, NewsFragmentBannerAdapter.ImageHolder> {

    private static final String TAG = "NewsFragmentBannerAdapt";
    public String BASE_URL = "http://124.93.196.45:10001";
    public List<NewsDetailsResult.RowsBean> newsDetailsList;
    public Context context;

    public NewsFragmentBannerAdapter(List<String> data, Context context) {
        super(data);
        this.context = context;
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup viewGroup, int i) {

        ImageView imageView = (ImageView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_banner_form, viewGroup, false);
        return new ImageHolder(imageView);

    }

    @Override
    public void onBindView(ImageHolder imageHolder, String s, int i, int i1) {

        Glide.with(imageHolder.itemView.getContext())
                .load(BASE_URL + mDatas.get(i))
                .error(R.mipmap.android_test_image)
                .into(imageHolder.iv_news_banner_form);

    }

    static class ImageHolder extends RecyclerView.ViewHolder {

        ImageView iv_news_banner_form;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            iv_news_banner_form = itemView.findViewById(R.id.iv_news_banner_form);
        }
    }
}
