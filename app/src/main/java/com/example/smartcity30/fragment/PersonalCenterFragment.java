package com.example.smartcity30.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartcity30.R;
import com.example.smartcity30.utils.ImageViewRadiusFormUtil;

public class PersonalCenterFragment extends Fragment {

    public View view;
    public ImageViewRadiusFormUtil iv_personal_avatar;
    public TextView tv_personal_username;
    public Button btn_personal_information;
    public Button btn_list_of_orders;
    public Button btn_my_wallet;
    public Button btn_my_points;
    public Button btn_feedback;
    public Button btn_change_the_password;
    public Button btn_sign_out;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_personal_center, container, false);
        initView();

        return view;
    }

    private void initView() {
        iv_personal_avatar = view.findViewById(R.id.iv_personal_avatar);
        tv_personal_username = view.findViewById(R.id.tv_personal_username);
        btn_personal_information = view.findViewById(R.id.btn_personal_information);
        btn_list_of_orders = view.findViewById(R.id.btn_list_of_orders);
        btn_my_wallet = view.findViewById(R.id.btn_my_wallet);
        btn_my_points = view.findViewById(R.id.btn_my_points);
        btn_feedback = view.findViewById(R.id.btn_feedback);
        btn_change_the_password = view.findViewById(R.id.btn_change_the_password);
        btn_sign_out = view.findViewById(R.id.btn_sign_out);
    }
}