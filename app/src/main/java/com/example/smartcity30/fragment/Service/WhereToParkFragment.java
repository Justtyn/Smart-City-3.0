package com.example.smartcity30.fragment.Service;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.adapter.ParkListAdapter;
import com.example.smartcity30.bean.ParkListInfoResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WhereToParkFragment extends Fragment {

    private static final String TAG = "WhereToParkFragment";
    public List<ParkListInfoResult.RowsBean> parkListDataList = new ArrayList<>();
    public String BASE_URL = "http://124.93.196.45:10001";
    public View view;
    public RecyclerView rv_where_to_park_park_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_where_to_park, container, false);
        parkListNetworkRequest();
        initRecyclerView();
        return view;
    }

    private void parkListNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ParkListInfoResult> parkListInfoResultCall = apiService.getParkListInfo();
        parkListInfoResultCall.enqueue(new Callback<ParkListInfoResult>() {
            @Override
            public void onResponse(@NonNull Call<ParkListInfoResult> call, @NonNull Response<ParkListInfoResult> response) {
                if (response.isSuccessful()) {
                    ParkListInfoResult parkListInfoResult = response.body();
                    if (parkListInfoResult != null) {
                        parkListDataList = parkListInfoResult.getRows();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParkListInfoResult> call, @NonNull Throwable throwable) {

            }
        });
    }

    private void initRecyclerView() {
        ParkListAdapter parkListAdapter = new ParkListAdapter(parkListDataList);
        rv_where_to_park_park_list = view.findViewById(R.id.rv_where_to_park_park_list);
        rv_where_to_park_park_list.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        rv_where_to_park_park_list.setAdapter(parkListAdapter);
    }
}