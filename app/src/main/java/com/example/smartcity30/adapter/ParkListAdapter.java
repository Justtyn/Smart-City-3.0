package com.example.smartcity30.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity30.R;
import com.example.smartcity30.bean.ParkListInfoResult;
import com.example.smartcity30.ParkListParkingRecordsActivity;

import java.util.List;

public class ParkListAdapter extends RecyclerView.Adapter<ParkListAdapter.MyViewHolder> {

    public List<ParkListInfoResult.RowsBean> parkListDataList;

    public ParkListAdapter(List<ParkListInfoResult.RowsBean> parkListDataList) {
        this.parkListDataList = parkListDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.where_to_park_list_form, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_where_to_park_list_parkName.setText(parkListDataList.get(position).getParkName());
        holder.tv_where_to_park_list_distance.setText("距离 : " + parkListDataList.get(position).getDistance() + " KM");
        holder.tv_where_to_park_list_vacancy.setText("剩余车位 : " + parkListDataList.get(position).getVacancy());

        if (parkListDataList.get(position).getOpen().equals("Y")) {
            holder.tv_where_to_park_list_whether_open.setText("对外开放 : " + "开放");
        } else {
            holder.tv_where_to_park_list_whether_open.setText("对外开放 : " + "暂未开放");
        }

        holder.tv_where_to_park_list_parkAddress.setText("地址 : " + parkListDataList.get(position).getAddress());
        holder.tv_where_to_park_list_starting_price.setText("起步价 : " + parkListDataList.get(position).getRates());
        holder.tv_where_to_park_list_priceCaps.setText("封顶价 : " + parkListDataList.get(position).getPriceCaps());
        holder.tv_where_to_park_list_parkTotal.setText("车位总数 : " + parkListDataList.get(position).getAllPark());

        holder.linearLayout_park_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ParkListParkingRecordsActivity.class);
                String parkName = parkListDataList.get(position).getParkName();
                String vacancy = parkListDataList.get(position).getVacancy();
                intent.putExtra("parkName",parkName);
                intent.putExtra("vacancy",vacancy);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return parkListDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout_park_list;
        TextView tv_where_to_park_list_parkName;
        TextView tv_where_to_park_list_distance;
        TextView tv_where_to_park_list_vacancy;
        TextView tv_where_to_park_list_whether_open;
        TextView tv_where_to_park_list_parkAddress;
        TextView tv_where_to_park_list_starting_price;
        TextView tv_where_to_park_list_priceCaps;
        TextView tv_where_to_park_list_parkTotal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout_park_list = itemView.findViewById(R.id.linearLayout_park_list);
            tv_where_to_park_list_parkName = itemView.findViewById(R.id.tv_where_to_park_list_parkName);
            tv_where_to_park_list_distance = itemView.findViewById(R.id.tv_where_to_park_list_distance);
            tv_where_to_park_list_vacancy = itemView.findViewById(R.id.tv_where_to_park_list_vacancy);
            tv_where_to_park_list_whether_open = itemView.findViewById(R.id.tv_where_to_park_list_whether_open);
            tv_where_to_park_list_parkAddress = itemView.findViewById(R.id.tv_where_to_park_list_parkAddress);
            tv_where_to_park_list_starting_price = itemView.findViewById(R.id.tv_where_to_park_list_starting_price);
            tv_where_to_park_list_priceCaps = itemView.findViewById(R.id.tv_where_to_park_list_priceCaps);
            tv_where_to_park_list_parkTotal = itemView.findViewById(R.id.tv_where_to_park_list_parkTotal);

        }
    }
}
