package com.example.smartcity30.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity30.bean.ParkListInfoResult;

import java.util.List;

public class ParkListAdapter extends RecyclerView.Adapter<ParkListAdapter.MyViewHolder> {

    public List<ParkListInfoResult.RowsBean> parkListDataList;

    public ParkListAdapter(List<ParkListInfoResult.RowsBean> parkListDataList) {
        this.parkListDataList = parkListDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
