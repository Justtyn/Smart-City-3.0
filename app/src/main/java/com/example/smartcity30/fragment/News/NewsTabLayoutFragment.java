package com.example.smartcity30.fragment.News;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcity30.R;
import com.example.smartcity30.bean.NewsCategoryResult;

import java.util.List;

public class NewsTabLayoutFragment extends Fragment {

    public View view;
    public TextView tv_news_tab_layout_fragment_news_category;
    public int pageIndex;
    public List<NewsCategoryResult.DataBean> newsCategoryList;

    public NewsTabLayoutFragment(int pageIndex, List<NewsCategoryResult.DataBean> newsCategoryList) {
        this.pageIndex = pageIndex;
        this.newsCategoryList = newsCategoryList;
    }

    public static NewsTabLayoutFragment newInstance(int pageIndex, List<NewsCategoryResult.DataBean> newsCategoryList) {

        return new NewsTabLayoutFragment(pageIndex, newsCategoryList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_news_tab_layout, container, false);

        tv_news_tab_layout_fragment_news_category = view.findViewById(R.id.tv_news_tab_layout_fragment_news_category);
        tv_news_tab_layout_fragment_news_category.setText(newsCategoryList.get(pageIndex).getId());

        return view;
    }
}