package com.example.smartcity30.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.adapter.GuidePageAdapter;
import com.example.smartcity30.adapter.HomeFragmentServiceGridListAdapter;
import com.example.smartcity30.adapter.NewsFragmentBannerAdapter;
import com.example.smartcity30.bean.GetAllServiceResult;
import com.example.smartcity30.bean.HomeFragmentBannerInfoResult;
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

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    public String BASE_URL = "http://124.93.196.45:10001";
    public View view;
    public SearchView sv_home_fragment;
    public Banner<String,NewsFragmentBannerAdapter> banner_home_fragment_ad;
    public RecyclerView rv_main_suggest_service_list;
    public TabLayout tab_layout_home_fragment_news_category_tab;
    public ViewPager2 vp2_home_fragment;
    public List<String> homeFragmentBannerImageUrlList = new ArrayList<>();
    private List<GetAllServiceResult.RowsBean> rowsBeanList;
    public List<String> newsCategoryList = new ArrayList<>();
    public List<Integer> newsTypeList = new ArrayList<>();
    public List<Fragment> newsCategoryFragmentList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        homeFragmentInfoNetworkRequest();
        return view;
    }

    private void homeFragmentInfoNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        // 主页轮播请求
        Call<HomeFragmentBannerInfoResult> homeFragmentBannerInfoResultCall = apiService.getHomeFragmentBannerInfo(2);
        homeFragmentBannerInfoResultCall.enqueue(new Callback<HomeFragmentBannerInfoResult>() {

            List<HomeFragmentBannerInfoResult.RowsBean> rowsBeanList;
            HomeFragmentBannerInfoResult homeFragmentBannerInfoResult;

            @Override
            public void onResponse(@NonNull Call<HomeFragmentBannerInfoResult> call, @NonNull Response<HomeFragmentBannerInfoResult> response) {
                if (response.code() == 200) {
                    homeFragmentBannerInfoResult = response.body();
                    if (homeFragmentBannerInfoResult != null) {
                        rowsBeanList = homeFragmentBannerInfoResult.getRows();
                    }
                    for (int i = 0; i < rowsBeanList.size(); i++) {
                        homeFragmentBannerImageUrlList.add(rowsBeanList.get(i).getAdvImg());
                    }
                    initBanner();
                }
            }

            @Override
            public void onFailure(@NonNull Call<HomeFragmentBannerInfoResult> call, @NonNull Throwable throwable) {

            }
        });

        // 服务列表请求
        Call<GetAllServiceResult> getAllServiceResultCall = apiService.getAllService();
        getAllServiceResultCall.enqueue(new Callback<GetAllServiceResult>() {
            @Override
            public void onResponse(@NonNull Call<GetAllServiceResult> call, @NonNull Response<GetAllServiceResult> response) {
                if (response.code() == 200) {
                    GetAllServiceResult getAllServiceResult = response.body();
                    if (getAllServiceResult != null) {
                        rowsBeanList = getAllServiceResult.getRows();
                    }
                    initRecyclerView();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetAllServiceResult> call, @NonNull Throwable throwable) {

            }
        });

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

        vp2_home_fragment.setAdapter(new FragmentStateAdapter(requireActivity().getSupportFragmentManager(), getLifecycle()) {
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

        new TabLayoutMediator(tab_layout_home_fragment_news_category_tab, vp2_home_fragment, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(newsCategoryList.get(position));
            }
        }).attach();
    }

    private void initRecyclerView() {
        HomeFragmentServiceGridListAdapter homeFragmentServiceGridListAdapter = new HomeFragmentServiceGridListAdapter(rowsBeanList);
        rv_main_suggest_service_list.setLayoutManager(new GridLayoutManager(this.getActivity(), 5));
        rv_main_suggest_service_list.setAdapter(homeFragmentServiceGridListAdapter);
    }

    private void initView() {
        sv_home_fragment = view.findViewById(R.id.sv_home_fragment);
        banner_home_fragment_ad = view.findViewById(R.id.banner_home_fragment_ad);
        rv_main_suggest_service_list = view.findViewById(R.id.rv_main_suggest_service_list);
        tab_layout_home_fragment_news_category_tab = view.findViewById(R.id.tab_layout_home_fragment_news_category_tab);
        vp2_home_fragment = view.findViewById(R.id.vp2_home_fragment);

    }

    private void initBanner() {
        banner_home_fragment_ad.setAdapter(new NewsFragmentBannerAdapter(homeFragmentBannerImageUrlList, requireActivity()));
        // 给 banner 添加生命周期观察者 自动管理 banner 的生命周期
        banner_home_fragment_ad.addBannerLifecycleObserver(requireActivity());
        // 设置 banner 轮播指示器
        banner_home_fragment_ad.setIndicator(new CircleIndicator(requireActivity()));
        banner_home_fragment_ad.setIndicatorSelectedColor(Color.RED);
        banner_home_fragment_ad.setBannerRound(25);
        // 设置指示器位置
        banner_home_fragment_ad.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        // 设置 viewpager 的切换效果
        banner_home_fragment_ad.addPageTransformer(new ZoomOutPageTransformer());
        // 设置自动轮播
        banner_home_fragment_ad.isAutoLoop(true);
        banner_home_fragment_ad.setLoopTime(3000);
        banner_home_fragment_ad.start();

    }

}