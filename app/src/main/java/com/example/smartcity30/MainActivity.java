package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.smartcity30.fragment.HomeFragment;
import com.example.smartcity30.fragment.NewsFragment;
import com.example.smartcity30.fragment.PartyConstructFragment;
import com.example.smartcity30.fragment.PersonalCenterFragment;
import com.example.smartcity30.fragment.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public Map<Integer, Fragment> fragmentMap = new HashMap<>();
    public int curFragmentIndex = 0;    // 默认 Fragment 索引
    public Fragment fragment;
    public FragmentTransaction fragmentTransaction;
    public FragmentManager fragmentManager;
    public BottomNavigationView bottom_navigation_view_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(null);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);

        initView();
        showMainFragment();
    }

    // 进入后默认显示 HomeFragment
    private void showMainFragment() {
        fragmentManager = getSupportFragmentManager();

        fragment = fragmentMap.get(curFragmentIndex);
        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment == null) {
            fragment = new HomeFragment();
            fragmentMap.put(curFragmentIndex, fragment);
            fragmentTransaction.add(R.id.fragment_container_view, fragment).setReorderingAllowed(true);
        }
        fragmentTransaction.show(fragment).commit();
    }

    private void initView() {
        bottom_navigation_view_main = findViewById(R.id.bottom_navigation_view_main);
        // 设置点击底部导航栏按钮事件
        bottom_navigation_view_main.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 为每个底部栏按钮分配索引
                switch (item.getItemId()) {
                    case R.id.item_nav_home:
                        curFragmentIndex = 0;
                        break;
                    case R.id.item_nav_service:
                        curFragmentIndex = 1;
                        break;
                    case R.id.item_nav_party_construct:
                        curFragmentIndex = 2;
                        break;
                    case R.id.item_nav_news:
                        curFragmentIndex = 3;
                        break;
                    case R.id.item_nav_personal_center:
                        curFragmentIndex = 4;
                        break;
                }

                fragment = fragmentMap.get(curFragmentIndex);
                fragmentTransaction = fragmentManager.beginTransaction();

                if (fragment == null) {
                    switch (curFragmentIndex) {
                        case 0:
                            fragment = new HomeFragment();
                            break;
                        case 1:
                            fragment = new ServiceFragment();
                            break;
                        case 2:
                            fragment = new PartyConstructFragment();
                            break;
                        case 3:
                            fragment = new NewsFragment();
                            break;
                        case 4:
                            fragment = new PersonalCenterFragment();
                            break;
                    }
                    fragmentMap.put(curFragmentIndex, fragment);
                    fragmentTransaction.add(R.id.fragment_container_view, fragment).setReorderingAllowed(true);
                }

                // 切换到其他 fragment 时隐藏已经创建的 fragment
                // 遍历 Map 隐藏 Fragment
                for (Map.Entry<Integer, Fragment> map : fragmentMap.entrySet()) {
                    fragmentTransaction.hide(map.getValue());
                }

                // 提交显示 fragment 的 transaction
                fragmentTransaction.show(fragment).commit();
                return true;
            }
        });

    }
}