package com.example.smartcity30.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity30.R;
import com.example.smartcity30.bean.ParkListParkingRecordsResult;

import java.util.ArrayList;
import java.util.List;

public class ParkListParkingRecordsAdapter extends RecyclerView.Adapter<ParkListParkingRecordsAdapter.MyViewHolder> {

    public List<ParkListParkingRecordsResult.RowsDTO> parkingRecordsList;
    public View view;

    public ParkListParkingRecordsAdapter(List<ParkListParkingRecordsResult.RowsDTO> parkingRecordsList) {
        this.parkingRecordsList = parkingRecordsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.park_list_parking_records_list_form, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return parkingRecordsList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_parking_records_plateNumber;
        TextView tv_parking_records_monetary;
        TextView tv_parking_records_entryTime;
        TextView tv_parking_records_outTime;
        TextView tv_parking_records_parkNum;
        TextView tv_parking_records_parkID;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_parking_records_plateNumber = itemView.findViewById(R.id.tv_parking_records_plateNumber);
            tv_parking_records_monetary = itemView.findViewById(R.id.tv_parking_records_monetary);
            tv_parking_records_entryTime = itemView.findViewById(R.id.tv_parking_records_entryTime);
            tv_parking_records_outTime = itemView.findViewById(R.id.tv_parking_records_outTime);
            tv_parking_records_parkNum = itemView.findViewById(R.id.tv_parking_records_parkNum);
            tv_parking_records_parkID = itemView.findViewById(R.id.tv_parking_records_parkID);
        }
    }
}
