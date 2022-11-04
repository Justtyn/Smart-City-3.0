package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.adapter.ParkListParkingRecordsAdapter;
import com.example.smartcity30.bean.ParkListParkingRecordsResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkListParkingRecordsActivity extends AppCompatActivity {

    public View view;
    public TextView tv_parking_records_parkName;
    public TextView tv_parking_records_vacancy;
    public RecyclerView rv_park_records_park_info;
    public List<ParkListParkingRecordsResult.RowsDTO> parkListParkingRecordsList = new ArrayList<>();
    public String BASE_URL = "http://124.93.196.45:10001";
    public String parkName, vacancy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_list_parking_records);

        initView();
        getInfoByIntent();
        getParkingRecordsNetworkRequest();
    }

    private void getInfoByIntent() {
        Intent intent = getIntent();
        parkName = intent.getStringExtra("parkName");
        vacancy = intent.getStringExtra("vacancy");
        tv_parking_records_parkName.setText(parkName);
        tv_parking_records_vacancy.setText("剩余车位 : " + vacancy);

    }

    private void getParkingRecordsNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ParkListParkingRecordsResult> parkListParkingRecordsResultCall = apiService.getParkingRecordsInfo(parkName);
        parkListParkingRecordsResultCall.enqueue(new Callback<ParkListParkingRecordsResult>() {
            @Override
            public void onResponse(@NonNull Call<ParkListParkingRecordsResult> call, @NonNull Response<ParkListParkingRecordsResult> response) {
                if (response.isSuccessful()) {
                    ParkListParkingRecordsResult parkListParkingRecordsResult = response.body();
                    if (parkListParkingRecordsResult != null) {
                        parkListParkingRecordsList = parkListParkingRecordsResult.getRows();
                        initRecyclerView();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParkListParkingRecordsResult> call, @NonNull Throwable throwable) {

            }
        });
    }

    private void initView() {
        tv_parking_records_parkName = findViewById(R.id.tv_parking_records_parkName);
        tv_parking_records_vacancy = findViewById(R.id.tv_parking_records_vacancy);
        rv_park_records_park_info = findViewById(R.id.rv_park_records_park_info);
    }

    private void initRecyclerView() {
        ParkListParkingRecordsAdapter parkListParkingRecordsAdapter = new ParkListParkingRecordsAdapter(parkListParkingRecordsList);
        rv_park_records_park_info.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_park_records_park_info.setAdapter(parkListParkingRecordsAdapter);
    }
}