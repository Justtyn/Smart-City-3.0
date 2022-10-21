package com.example.smartcity30.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.R;
import com.example.smartcity30.adapter.ServiceFragmentGridListAdapter;
import com.example.smartcity30.bean.GetAllServiceResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFragment extends Fragment {

    private static final String TAG = "ServiceFragment";
    public View view;
    public List<GetAllServiceResult.RowsBean> allServiceDataList = new ArrayList<>();
    public SearchView sv_main_search;
    public RecyclerView rv_main_fruit_list;
    public String BASE_URL = "http://124.93.196.45:10001";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service, container, false);

        getAllServiceNetworkRequest();
        initView();
        return view;
    }

    private void initView() {
        sv_main_search = view.findViewById(R.id.sv_main_search);
        rv_main_fruit_list = view.findViewById(R.id.rv_main_fruit_list);

    }

    private void initRecyclerView() {
        ServiceFragmentGridListAdapter serviceFragmentGridListAdapter = new ServiceFragmentGridListAdapter(allServiceDataList);
        rv_main_fruit_list.setLayoutManager(new GridLayoutManager(this.getActivity(), 3));
        rv_main_fruit_list.setAdapter(serviceFragmentGridListAdapter);
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
                if (response.code() == 200) {
                    GetAllServiceResult getAllServiceResult = response.body();
                    if (getAllServiceResult != null) {
                        allServiceDataList = getAllServiceResult.getRows();
                    }
                    for (int i = 0; i < allServiceDataList.size(); i++) {
                        GetAllServiceResult.RowsBean rowsBean = new GetAllServiceResult.RowsBean();
                        rowsBean.setServiceName(allServiceDataList.get(i).getServiceName());
                        rowsBean.setImgUrl(allServiceDataList.get(i).getImgUrl());
                        Log.d(TAG, "onResponse: " + allServiceDataList.get(i).getServiceName());
                    }
                    initRecyclerView();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetAllServiceResult> call, @NonNull Throwable throwable) {

            }
        });
    }


}