package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.example.smartcity30.bean.GetAllServiceResult;
import com.example.smartcity30.fragment.Service.CitySubwayFragment;
import com.example.smartcity30.fragment.Service.DataAnalyseFragment;
import com.example.smartcity30.fragment.Service.DonateWithLoveFragment;
import com.example.smartcity30.fragment.Service.EventManagmentFragment;
import com.example.smartcity30.fragment.Service.FindHouseFragment;
import com.example.smartcity30.fragment.Service.FindJobFragment;
import com.example.smartcity30.fragment.Service.HospitalAppointmentsFragment;
import com.example.smartcity30.fragment.Service.LivingPaymentsFragment;
import com.example.smartcity30.fragment.Service.LogisticsInquiriesFragment;
import com.example.smartcity30.fragment.Service.PetHospitalFragment;
import com.example.smartcity30.fragment.Service.SmartBusFragment;
import com.example.smartcity30.fragment.Service.SmartTrafficManagmentFragment;
import com.example.smartcity30.fragment.Service.TakeawayOrdersFragment;
import com.example.smartcity30.fragment.Service.WatchMovieFragment;
import com.example.smartcity30.fragment.Service.WhereToParkFragment;
import com.example.smartcity30.fragment.Service.YouthStationFragment;
import com.example.smartcity30.utils.SharedPreferencesUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceMainActivity extends AppCompatActivity {

    private static final String TAG = "ServiceMainActivity";
    public String BASE_URL = "http://124.93.196.45:10001";
    public List<Fragment> serviceFragmentList = new ArrayList<>();
    public List<String> serviceNameList = new ArrayList<>();
    public HorizontalScrollView horizontalScrollView;
    public TabLayout tabLayout;
    public ViewPager2 viewPager2;
    public int serviceIndex;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        getServiceIndexBySPUtil();
        initView();
        getAllServiceNetworkRequest();
    }


    private void getServiceIndexBySPUtil() {
        serviceIndex = SharedPreferencesUtil.getInt(getApplicationContext(), "serviceIndex", 0);
    }

    private void getAllServiceNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<GetAllServiceResult> getAllServiceResultCall = apiService.getAllService();
        getAllServiceResultCall.enqueue(new Callback<GetAllServiceResult>() {
            @Override
            public void onResponse(@NonNull Call<GetAllServiceResult> call, @NonNull Response<GetAllServiceResult> response) {
                if (response.isSuccessful()) {
                    GetAllServiceResult getAllServiceResult = response.body();
                    if (getAllServiceResult != null) {
                        List<GetAllServiceResult.RowsBean> rowsBeanList = getAllServiceResult.getRows();
                        for (int i = 0; i < rowsBeanList.size(); i++) {
                            serviceNameList.add(rowsBeanList.get(i).getServiceName());
                        }
                        initViewPager2AndTabLayout();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetAllServiceResult> call, @NonNull Throwable throwable) {

            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.tb_service_main_activity);
        horizontalScrollView = findViewById(R.id.horizontal_sv_service_main_activity);
        tabLayout = findViewById(R.id.tab_layout_service_main_activity);
        viewPager2 = findViewById(R.id.vp2_service_main_activity);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initViewPager2AndTabLayout() {
        WhereToParkFragment whereToParkFragment = new WhereToParkFragment();
        CitySubwayFragment citySubwayFragment = new CitySubwayFragment();
        SmartBusFragment smartBusFragment = new SmartBusFragment();
        HospitalAppointmentsFragment hospitalAppointmentsFragment = new HospitalAppointmentsFragment();
        SmartTrafficManagmentFragment smartTrafficManagmentFragment = new SmartTrafficManagmentFragment();
        LivingPaymentsFragment livingPaymentsFragment = new LivingPaymentsFragment();
        TakeawayOrdersFragment takeawayOrdersFragment = new TakeawayOrdersFragment();
        FindHouseFragment findHouseFragment = new FindHouseFragment();
        WatchMovieFragment watchMovieFragment = new WatchMovieFragment();
        FindJobFragment findJobFragment = new FindJobFragment();
        EventManagmentFragment eventManagmentFragment = new EventManagmentFragment();
        PetHospitalFragment petHospitalFragment = new PetHospitalFragment();
        LogisticsInquiriesFragment logisticsInquiriesFragment = new LogisticsInquiriesFragment();
        DonateWithLoveFragment donateWithLoveFragment = new DonateWithLoveFragment();
        YouthStationFragment youthStationFragment = new YouthStationFragment();
        DataAnalyseFragment dataAnalyseFragment = new DataAnalyseFragment();

        serviceFragmentList.add(whereToParkFragment);
        serviceFragmentList.add(citySubwayFragment);
        serviceFragmentList.add(smartBusFragment);
        serviceFragmentList.add(hospitalAppointmentsFragment);
        serviceFragmentList.add(smartTrafficManagmentFragment);
        serviceFragmentList.add(livingPaymentsFragment);
        serviceFragmentList.add(takeawayOrdersFragment);
        serviceFragmentList.add(findHouseFragment);
        serviceFragmentList.add(watchMovieFragment);
        serviceFragmentList.add(findJobFragment);
        serviceFragmentList.add(eventManagmentFragment);
        serviceFragmentList.add(petHospitalFragment);
        serviceFragmentList.add(logisticsInquiriesFragment);
        serviceFragmentList.add(donateWithLoveFragment);
        serviceFragmentList.add(youthStationFragment);
        serviceFragmentList.add(dataAnalyseFragment);

        viewPager2.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return serviceFragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return serviceNameList.size();
            }
        });


        new TabLayoutMediator(tabLayout, viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(serviceNameList.get(position));
            }
        }).attach();

        Objects.requireNonNull(tabLayout.getTabAt(serviceIndex)).select();
        int indexX = horizontalScrollView.getHeight() / (serviceNameList.size() + 1);
        Log.d(TAG, "initViewPager2AndTabLayout: " + indexX);
        horizontalScrollView.scrollTo(serviceIndex * indexX, 0);
    }
}