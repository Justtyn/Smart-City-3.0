package com.example.smartcity30.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartcity30.ApiService;
import com.example.smartcity30.LoginActivity;
import com.example.smartcity30.PersonalCenterChangePasswordActivity;
import com.example.smartcity30.PersonalCenterFeedbackActivity;
import com.example.smartcity30.PersonalCenterMyPointActivity;
import com.example.smartcity30.PersonalCenterOrderListActivity;
import com.example.smartcity30.PersonalCenterPersonMyWalletActivity;
import com.example.smartcity30.PersonalCenterPersonalInfoActivity;
import com.example.smartcity30.R;
import com.example.smartcity30.bean.GetUserInfoResult;
import com.example.smartcity30.utils.ImageViewRadiusFormUtil;
import com.example.smartcity30.utils.SharedPreferencesUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalCenterFragment extends Fragment {

    private static final String TAG = "PersonalCenterFragment";
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
    public String TOKEN;
    public String BASE_URL = "http://124.93.196.45:10001";
    public String userName, email, phoneNum, sex, idCard;
    public int userId, score;
    public double balance;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_personal_center, container, false);
        initView();
        getTokenBySPUtil();
        personalCenterNetworkRequest();

        return view;
    }

    private void getTokenBySPUtil() {
        TOKEN = SharedPreferencesUtil.getString(requireActivity().getApplicationContext(), "TOKEN", "");
        Log.d(TAG, "getTokenBySPUtil: " + TOKEN);
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

        btn_personal_information.setOnClickListener(v -> {
            intent = new Intent(requireActivity(), PersonalCenterPersonalInfoActivity.class);
            personalCenterNetworkRequest();
            intent.putExtra("userName", userName);
            intent.putExtra("email", email);
            intent.putExtra("phoneNum", phoneNum);
            intent.putExtra("sex", sex);
            intent.putExtra("idCard", idCard);
            intent.putExtra("userId", String.valueOf(userId));
            startActivity(intent);
        });
        btn_list_of_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(requireActivity(), PersonalCenterOrderListActivity.class);
                startActivity(intent);
            }
        });
        btn_my_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(requireActivity(), PersonalCenterPersonMyWalletActivity.class);
                startActivity(intent);
            }
        });
        btn_my_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(requireActivity(), PersonalCenterMyPointActivity.class);
                startActivity(intent);
            }
        });
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(requireActivity(), PersonalCenterFeedbackActivity.class);
                startActivity(intent);
            }
        });
        btn_change_the_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(requireActivity(), PersonalCenterChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
    }

    public void personalCenterNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<GetUserInfoResult> getUserInfoResultCall = apiService.getUserInfo(TOKEN);
        getUserInfoResultCall.enqueue(new Callback<GetUserInfoResult>() {
            GetUserInfoResult getUserInfoResult;
            GetUserInfoResult.UserBean userInfoUserBeanData;

            @Override
            public void onResponse(@NonNull Call<GetUserInfoResult> call, @NonNull Response<GetUserInfoResult> response) {
                if (response.code() == 200) {
                    getUserInfoResult = response.body();
                    if (getUserInfoResult != null) {
                        userInfoUserBeanData = getUserInfoResult.getUser();
                        Log.d(TAG, "onResponse: " + userInfoUserBeanData);

                        userName = userInfoUserBeanData.getUserName();
                        email = userInfoUserBeanData.getEmail();
                        phoneNum = userInfoUserBeanData.getPhonenumber();
                        sex = userInfoUserBeanData.getSex();
                        idCard = userInfoUserBeanData.getIdCard();
                        userId = userInfoUserBeanData.getUserId();
                        balance = userInfoUserBeanData.getBalance();
                        score = userInfoUserBeanData.getScore();
                    }
                    tv_personal_username.setText(userName);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetUserInfoResult> call, @NonNull Throwable throwable) {

            }
        });
    }
}