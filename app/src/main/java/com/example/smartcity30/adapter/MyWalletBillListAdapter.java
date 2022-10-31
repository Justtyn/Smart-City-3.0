package com.example.smartcity30.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity30.R;
import com.example.smartcity30.bean.AmountsChangesInfoResult;
import com.example.smartcity30.utils.ImageViewRadiusFormUtil;

import java.util.List;

public class MyWalletBillListAdapter extends RecyclerView.Adapter<MyWalletBillListAdapter.MyViewHolder> {

    List<AmountsChangesInfoResult.RowsBean> amountsChangesInfoResultList;

    public MyWalletBillListAdapter(List<AmountsChangesInfoResult.RowsBean> amountsChangesInfoResultList) {
        this.amountsChangesInfoResultList = amountsChangesInfoResultList;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageViewRadiusFormUtil iv_my_wallet_bill_change_type;
        TextView tv_my_wallet_bill_app_type;
        TextView tv_my_wallet_bill_change_time;
        TextView tv_my_wallet_bill_change_amount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_my_wallet_bill_change_type = itemView.findViewById(R.id.iv_my_wallet_bill_change_type);
            tv_my_wallet_bill_app_type = itemView.findViewById(R.id.tv_my_wallet_bill_app_type);
            tv_my_wallet_bill_change_time = itemView.findViewById(R.id.tv_my_wallet_bill_change_time);
            tv_my_wallet_bill_change_amount = itemView.findViewById(R.id.tv_my_wallet_bill_change_amount);
        }
    }

    @NonNull
    @Override
    public MyWalletBillListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_wallet_bill_list_form, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyWalletBillListAdapter.MyViewHolder holder, int position) {

        if (amountsChangesInfoResultList.get(position).getChangeType().equals("收入")) {
            holder.iv_my_wallet_bill_change_type.setImageResource(R.mipmap.revenue);
            holder.tv_my_wallet_bill_app_type.setText(amountsChangesInfoResultList.get(position).getAppType());
            holder.tv_my_wallet_bill_change_time.setText(amountsChangesInfoResultList.get(position).getChangeTime());
            String changeAmount = "+ " + amountsChangesInfoResultList.get(position).getChangeAmount();
            holder.tv_my_wallet_bill_change_amount.setText(changeAmount);
        } else {
            holder.iv_my_wallet_bill_change_type.setImageResource(R.mipmap.spending);
            holder.tv_my_wallet_bill_app_type.setText(amountsChangesInfoResultList.get(position).getAppType());
            holder.tv_my_wallet_bill_change_time.setText(amountsChangesInfoResultList.get(position).getChangeTime());
            String changeAmount = "- " + amountsChangesInfoResultList.get(position).getChangeAmount();
            holder.tv_my_wallet_bill_change_amount.setText(changeAmount);
        }

    }

    @Override
    public int getItemCount() {
        return amountsChangesInfoResultList.size();
    }
}
