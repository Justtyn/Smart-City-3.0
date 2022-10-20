package com.example.smartcity30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity30.R;
import com.example.smartcity30.bean.NewsDetailsResult;

import java.util.List;

public class NewsFragmentNewsListAdapter extends RecyclerView.Adapter<NewsFragmentNewsListAdapter.MyViewHolder> {

    public List<NewsDetailsResult.RowsBean> newsDetailsDataList;
    public Context context;

    public NewsFragmentNewsListAdapter(List<NewsDetailsResult.RowsBean> newsDetailsDataList, Context context) {
        this.newsDetailsDataList = newsDetailsDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_form, parent, false);
        return new NewsFragmentNewsListAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
