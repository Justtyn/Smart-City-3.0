package com.example.smartcity30.fragment.News.viewPagerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.R;


public class TodayNewsFragment extends Fragment {

    public View view;
    public int newsTypeIndex;
    public RecyclerView rv_news_today_news;

    public TodayNewsFragment(int newsTypeIndex) {
        this.newsTypeIndex = newsTypeIndex;
    }

    public static TodayNewsFragment newInstance(int newsTypeIndex) {
        return new TodayNewsFragment(newsTypeIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_today_news, container, false);
        initView();
        return view;
    }

    private void initView() {
        rv_news_today_news = view.findViewById(R.id.rv_news_today_news);
        rv_news_today_news.setAdapter(new );
    }
}