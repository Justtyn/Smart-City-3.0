package com.example.smartcity30.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity30.R;
import com.example.smartcity30.ServiceMainActivity;
import com.example.smartcity30.bean.GetAllServiceResult;
import com.example.smartcity30.fragment.ServiceFragment;
import com.example.smartcity30.utils.SharedPreferencesUtil;

import java.util.List;

public class ServiceFragmentGridListAdapter extends RecyclerView.Adapter<ServiceFragmentGridListAdapter.MyViewHolder> {

    public List<GetAllServiceResult.RowsBean> allServiceDataList;
    private View view;

    public ServiceFragmentGridListAdapter(List<GetAllServiceResult.RowsBean> allServiceDataList) {
        this.allServiceDataList = allServiceDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_list_item_form, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String serviceName = allServiceDataList.get(position).getServiceName();
        String BASE_URL = "http://124.93.196.45:10001";

        Glide.with(holder.itemView.getContext()).load(BASE_URL + allServiceDataList.get(position).getImgUrl()).error(R.drawable.ic_baseline_directions_bus_24).into(holder.iv_service_item_form_image);

        holder.tv_service_item_form_name.setText(serviceName);
        holder.iv_service_item_form_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfoBySPUtil(position);
                Intent intent = new Intent(v.getContext(), ServiceMainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void saveUserInfoBySPUtil(int serviceIndex) {
        SharedPreferencesUtil.putInt(view.getContext(), "serviceIndex", serviceIndex);
    }

    @Override
    public int getItemCount() {
        return allServiceDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_service_item_form_image;
        TextView tv_service_item_form_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_service_item_form_image = itemView.findViewById(R.id.iv_service_item_form_image);
            tv_service_item_form_name = itemView.findViewById(R.id.tv_service_item_form_name);
        }
    }
}
