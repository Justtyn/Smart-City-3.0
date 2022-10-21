package com.example.smartcity30.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.adapter.NewsFragmentBannerAdapter;
import com.example.smartcity30.bean.NewsCategoryResult;
import com.example.smartcity30.bean.NewsDetailsResult;
import com.example.smartcity30.fragment.News.viewPagerFragment.CulturalTourismFragment;
import com.example.smartcity30.fragment.News.viewPagerFragment.EconomicDevelopmentFragment;
import com.example.smartcity30.fragment.News.viewPagerFragment.PolicyAnalyzingFragment;
import com.example.smartcity30.fragment.News.viewPagerFragment.TechnologicalInnovationFragment;
import com.example.smartcity30.fragment.News.viewPagerFragment.ThematicFocusFragment;
import com.example.smartcity30.fragment.News.viewPagerFragment.TodayNewsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

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
    public List<String> newsCategoryList = new ArrayList<>();
    public List<Integer> newsTypeList = new ArrayList<>();
    public List<NewsDetailsResult.RowsBean> newsDetailsDataList;
    public List<String> newsDetailsImageUrlList = new ArrayList<>();
    public NewsFragmentBannerAdapter newsFragmentBannerAdapter;
    public List<Fragment> newsCategoryFragmentList = new ArrayList<>();

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

            public NewsCategoryResult newsCategoryResult;
            public List<NewsCategoryResult.DataBean> newsCategoryDataList;

            @Override
            public void onResponse(@NonNull Call<NewsCategoryResult> call, @NonNull Response<NewsCategoryResult> response) {
                if (response.code() == 200) {
                    newsCategoryResult = response.body();
                    if (newsCategoryResult != null) {
                        newsCategoryDataList = newsCategoryResult.getData();
                    }

                    for (int i = 0; i < newsCategoryDataList.size(); i++) {
                        newsCategoryList.add(newsCategoryDataList.get(i).getName());
                        newsTypeList.add(newsCategoryDataList.get(i).getId());
                    }
                    initViewPager2AndTabLayout();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsCategoryResult> call, @NonNull Throwable throwable) {

            }
        });

        // 新闻详情请求
        Call<NewsDetailsResult> newsDetailsResultCall = apiService.getNewsDetails();
        newsDetailsResultCall.enqueue(new Callback<NewsDetailsResult>() {

            NewsDetailsResult newsDetailsResult;

            @Override
            public void onResponse(@NonNull Call<NewsDetailsResult> call, @NonNull Response<NewsDetailsResult> response) {
                if (response.code() == 200) {
                    newsDetailsResult = response.body();
                    if (newsDetailsResult != null) {
                        newsDetailsDataList = newsDetailsResult.getRows();
                    }
                    for (int i = 0; i < newsDetailsDataList.size(); i++) {
                        newsDetailsImageUrlList.add(newsDetailsDataList.get(i).getCover());
                    }
                    initBanner();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsDetailsResult> call, @NonNull Throwable throwable) {

            }
        });
    }

    private void initBanner() {

        banner_news_fragment_ad.setAdapter(new NewsFragmentBannerAdapter(newsDetailsImageUrlList, newsDetailsDataList, requireActivity()));
        // 给 banner 添加生命周期观察者 自动管理 banner 的生命周期
        banner_news_fragment_ad.addBannerLifecycleObserver(requireActivity());
//        // 设置 banner 轮播指示器
//        banner_news_fragment_ad.setIndicator(new CircleIndicator(requireActivity()));
//        // 设置指示器位置
//        banner_news_fragment_ad.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        // 设置 viewpager 的切换效果
        banner_news_fragment_ad.addPageTransformer(new ZoomOutPageTransformer());
        // 设置自动轮播
        banner_news_fragment_ad.isAutoLoop(true);
        banner_news_fragment_ad.setLoopTime(3000);
        banner_news_fragment_ad.start();

    }

    private void initViewPager2AndTabLayout() {
        TodayNewsFragment todayNewsFragment = TodayNewsFragment.newInstance(newsTypeList.get(0));
        ThematicFocusFragment thematicFocusFragment = ThematicFocusFragment.newInstance(newsTypeList.get(1));
        PolicyAnalyzingFragment policyAnalyzingFragment = PolicyAnalyzingFragment.newInstance(newsTypeList.get(2));
        EconomicDevelopmentFragment economicDevelopmentFragment = EconomicDevelopmentFragment.newInstance(newsTypeList.get(3));
        CulturalTourismFragment culturalTourismFragment = CulturalTourismFragment.newInstance(newsTypeList.get(4));
        TechnologicalInnovationFragment technologicalInnovationFragment = TechnologicalInnovationFragment.newInstance(newsTypeList.get(5));

        newsCategoryFragmentList.add(todayNewsFragment);
        newsCategoryFragmentList.add(thematicFocusFragment);
        newsCategoryFragmentList.add(policyAnalyzingFragment);
        newsCategoryFragmentList.add(economicDevelopmentFragment);
        newsCategoryFragmentList.add(culturalTourismFragment);
        newsCategoryFragmentList.add(technologicalInnovationFragment);
        vp2_news_fragment.setAdapter(new FragmentStateAdapter(requireActivity().getSupportFragmentManager(), getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return newsCategoryFragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return newsTypeList.size();
            }
        });

        new TabLayoutMediator(tab_layout_news_fragment_tab, vp2_news_fragment, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(newsCategoryList.get(position));
            }
        }).attach();

    }
}
