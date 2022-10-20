package com.example.smartcity30.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.bean.NewsCategoryResult;
import com.example.smartcity30.bean.NewsDetailsResult;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment {

    private static final String TAG = "NewsFragment";
    public String BASE_URL = "http://124.93.196.45:10001";
    public View view;
    public Banner banner_news_fragment_ad;
    public TabLayout tab_layout_news_fragment_tab;
    public ViewPager2 vp2_news_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);

        getNewsInfoNetworkRequest();
        initView();

        return view;
    }

    private void initView() {
        banner_news_fragment_ad = view.findViewById(R.id.banner_news_fragment_ad);
        tab_layout_news_fragment_tab = view.findViewById(R.id.tab_layout_news_fragment_tab);
        vp2_news_fragment = view.findViewById(R.id.vp2_news_fragment);

        initBanner();
        initViewPager2AndTabLayout();
    }

    private void getNewsInfoNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        // 新闻分类请求
        Call<NewsCategoryResult> newsCategoryResultCall = apiService.getNewsCategory();
        newsCategoryResultCall.enqueue(new Callback<NewsCategoryResult>() {
            @Override
            public void onResponse(@NonNull Call<NewsCategoryResult> call, @NonNull Response<NewsCategoryResult> response) {

            }

            @Override
            public void onFailure(@NonNull Call<NewsCategoryResult> call, @NonNull Throwable throwable) {

            }
        });
        // 新闻详情请求
        Call<NewsDetailsResult> newsDetailsResultCall = apiService.getNewsDetails();
        newsDetailsResultCall.enqueue(new Callback<NewsDetailsResult>() {
            @Override
            public void onResponse(Call<NewsDetailsResult> call, Response<NewsDetailsResult> response) {

            }

            @Override
            public void onFailure(Call<NewsDetailsResult> call, Throwable throwable) {

            }
        });

    }

    private void initBanner() {

    }

    private void initViewPager2AndTabLayout() {

    }
}
