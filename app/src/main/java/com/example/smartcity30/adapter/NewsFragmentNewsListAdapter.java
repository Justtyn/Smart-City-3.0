package com.example.smartcity30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.bean.NewsCategoryResult;
import com.example.smartcity30.bean.NewsDetailsResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragmentNewsListAdapter extends RecyclerView.Adapter<NewsFragmentNewsListAdapter.MyViewHolder> {

    public List<NewsDetailsResult.RowsBean> newsDetailsDataList;
    public Context context;
    public String BASE_URL = "http://124.93.196.45:10001";

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

        if (newsDetailsDataList != null) {

            Glide.with(holder.itemView.getContext()).load(BASE_URL + newsDetailsDataList.get(position).getCover()).error(R.drawable.ic_baseline_directions_bus_24).into(holder.iv_image);

            holder.tv_title.setText(newsDetailsDataList.get(position).getTitle());
            holder.tv_title.setSelected(true);
            holder.tv_content.setText(newsDetailsDataList.get(position).getContent());
            holder.tv_createTime.setText(newsDetailsDataList.get(position).getCreateTime());
            String likeNum = String.valueOf(newsDetailsDataList.get(position).getLikeNum());
            holder.tv_likeNum.setText(likeNum);
            String readNum = String.valueOf(newsDetailsDataList.get(position).getReadNum());
            holder.tv_readNum.setText(readNum);
            String commentNum = String.valueOf(newsDetailsDataList.get(position).getCommentNum());
            holder.tv_commentNum.setText(commentNum);

        }

    }

    @Override
    public int getItemCount() {
        return newsDetailsDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_image;
        TextView tv_title;
        TextView tv_content;
        TextView tv_createTime;
        TextView tv_likeNum;
        TextView tv_readNum;
        TextView tv_commentNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_image = itemView.findViewById(R.id.iv_news_list_form_image);
            tv_title = itemView.findViewById(R.id.tv_news_list_form_title);
            tv_content = itemView.findViewById(R.id.tv_news_list_form_content);
            tv_createTime = itemView.findViewById(R.id.tv_news_list_form_date);
            tv_likeNum = itemView.findViewById(R.id.tv_news_list_form_likeNum);
            tv_readNum = itemView.findViewById(R.id.tv_news_list_form_readNum);
            tv_commentNum = itemView.findViewById(R.id.tv_news_list_form_commentNum);

        }
    }

}
