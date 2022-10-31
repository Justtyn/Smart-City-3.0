package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.adapter.MyWalletBillListAdapter;
import com.example.smartcity30.bean.AmountsChangesInfoResult;
import com.example.smartcity30.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyWalletBillActivity extends AppCompatActivity {

    public static final String TAG = "MyWalletBillFragment";
    public String TOKEN;
    public String BASE_URL = "http://124.93.196.45:10001";
    public Toolbar tb_my_wallet_bill_top_bar;
    public RecyclerView rv_my_wallet_bill;
    public List<AmountsChangesInfoResult.RowsBean> amountsChangesInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet_bill);

        getTokenBySPUtil();
        initView();
        amountsChangesNetworkRequest();
    }

    public void initView() {
        tb_my_wallet_bill_top_bar = findViewById(R.id.tb_my_wallet_bill_top_bar);
        tb_my_wallet_bill_top_bar.setNavigationOnClickListener(v -> finish());
        rv_my_wallet_bill = findViewById(R.id.rv_my_wallet_bill);
    }

    public void amountsChangesNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<AmountsChangesInfoResult> amountsChangesInfoResultCall = apiService.amountsChangesInfo(TOKEN);
        amountsChangesInfoResultCall.enqueue(new Callback<AmountsChangesInfoResult>() {
            @Override
            public void onResponse(@NonNull Call<AmountsChangesInfoResult> call, @NonNull Response<AmountsChangesInfoResult> response) {
                if (response.code() == 200) {
                    AmountsChangesInfoResult amountsChangesInfoResult = response.body();
                    if (amountsChangesInfoResult != null) {
                        amountsChangesInfoList = amountsChangesInfoResult.getRows();
                        initRecyclerView();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AmountsChangesInfoResult> call, @NonNull Throwable throwable) {

            }
        });
    }


    public void getTokenBySPUtil() {
        TOKEN = SharedPreferencesUtil.getString(getApplicationContext(), "TOKEN", "");
    }

    public void initRecyclerView() {
        MyWalletBillListAdapter myWalletBillListAdapter = new MyWalletBillListAdapter(amountsChangesInfoList);
        rv_my_wallet_bill.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_my_wallet_bill.setAdapter(myWalletBillListAdapter);
    }
}