package com.example.smartcity30.fragment.Service.WhereToPark;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcity30.R;
import com.example.smartcity30.adapter.ParkListParkingRecordsAdapter;
import com.example.smartcity30.bean.ParkListParkingRecordsResult;

import java.util.ArrayList;
import java.util.List;

public class ParkListParkingRecordsFragment extends Fragment {

    public View view;
    public TextView tv_park_records_parkName;
    public RecyclerView rv_park_records_park_info;
    public List<ParkListParkingRecordsResult.RowsDTO> parkListParkingRecordsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_park_list_parking_records, container, false);

        initView();

        return view;
    }

    private void initView() {
        tv_park_records_parkName = view.findViewById(R.id.tv_park_records_parkName);
        rv_park_records_park_info = view.findViewById(R.id.rv_park_records_park_info);

        ParkListParkingRecordsAdapter parkListParkingRecordsAdapter = new ParkListParkingRecordsAdapter(parkListParkingRecordsList);
        rv_park_records_park_info.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        rv_park_records_park_info.setAdapter(parkListParkingRecordsAdapter);
    }
}