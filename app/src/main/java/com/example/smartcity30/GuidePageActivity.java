package com.example.smartcity30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.smartcity30.adapter.GuidePageAdapter;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class GuidePageActivity extends AppCompatActivity {

    public Banner banner_guide;
    public Button btn_guide_skip;
    public Button btn_guide_enter;
    public Button btn_guide_network_set;
    public List<Integer> guideImageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏设置
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);

        initView();
        initList();
        initBanner();
        onClickView();
    }

    private void initList() {
        guideImageList.add(R.mipmap.guide_page_1);
        guideImageList.add(R.mipmap.guide_page_2);
        guideImageList.add(R.mipmap.guide_page_3);
        guideImageList.add(R.mipmap.guide_page_4);
        guideImageList.add(R.mipmap.guide_page_5);
    }

    private void onClickView() {
        btn_guide_skip.setOnClickListener(v -> {
            Intent intent = new Intent(GuidePageActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initBanner() {
        // 将 banner 与 Adapter绑定 并传入 Guide Page 图片资源数据
        banner_guide.setAdapter(new GuidePageAdapter(guideImageList));
        // 给 banner 添加生命周期观察者 自动管理 banner 的生命周期
        banner_guide.addBannerLifecycleObserver(this);
        // 设置 banner 轮播指示器
        banner_guide.setIndicator(new CircleIndicator(this));
        // 设置指示器位置
        banner_guide.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        banner_guide.getViewPager2().registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 4) {
                    btn_guide_enter.setVisibility(View.VISIBLE);
                    btn_guide_network_set.setVisibility(View.VISIBLE);
                } else {
                    btn_guide_enter.setVisibility(View.GONE);
                    btn_guide_network_set.setVisibility(View.GONE);
                }
                if (position == 0) {
                    btn_guide_skip.setVisibility(View.VISIBLE);
                } else {
                    btn_guide_skip.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    private void initView() {
        banner_guide = findViewById(R.id.banner_guide);
        btn_guide_skip = findViewById(R.id.btn_guide_skip);
        btn_guide_enter = findViewById(R.id.btn_guide_enter);
        btn_guide_network_set = findViewById(R.id.btn_guide_network_set);
    }
}