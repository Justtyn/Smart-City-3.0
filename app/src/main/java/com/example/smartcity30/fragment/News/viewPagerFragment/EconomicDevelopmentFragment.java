package com.example.smartcity30.fragment.News.viewPagerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.adapter.NewsFragmentNewsListAdapter;
import com.example.smartcity30.bean.NewsDetailsResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EconomicDevelopmentFragment extends Fragment {

    public View view;
    public int newsTypeIndex;
    public RecyclerView rv_economic_development_news;
    public String BASE_URL = "http://124.93.196.45:10001";
    public List<NewsDetailsResult.RowsBean> newsDetailsDataList = new ArrayList<>();

    public EconomicDevelopmentFragment(int newsTypeIndex) {
        this.newsTypeIndex = newsTypeIndex;
    }

    public static EconomicDevelopmentFragment newInstance(int newsTypeIndex) {
        return new EconomicDevelopmentFragment(newsTypeIndex);
    }

    private void initView() {
        rv_economic_development_news = view.findViewById(R.id.rv_economic_development_news);
        rv_economic_development_news.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_economic_development_news.setAdapter(new NewsFragmentNewsListAdapter(newsDetailsDataList, requireActivity()));
    }

    private void getNewsInfoNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        // 新闻详情请求
        Call<NewsDetailsResult> newsDetailsResultCall = apiService.getNewsDetailsByType(newsTypeIndex);
        newsDetailsResultCall.enqueue(new Callback<NewsDetailsResult>() {

            NewsDetailsResult newsDetailsResult;

            @Override
            public void onResponse(Call<NewsDetailsResult> call, Response<NewsDetailsResult> response) {
                if (response.code() == 200) {
                    newsDetailsResult = response.body();
                    if (newsDetailsResult != null) {
                        newsDetailsDataList = newsDetailsResult.getRows();
                    }
                    initView();
                }
            }

            @Override
            public void onFailure(Call<NewsDetailsResult> call, Throwable throwable) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_economic_development, container, false);
        getNewsInfoNetworkRequest();

        return view;
    }
}