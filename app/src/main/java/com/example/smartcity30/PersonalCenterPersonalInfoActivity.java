package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.smartcity30.bean.ChangeUserInfoInfo;
import com.example.smartcity30.bean.ChangeUserInfoResult;
import com.example.smartcity30.bean.GetUserInfoResult;
import com.example.smartcity30.fragment.PersonalCenterFragment;
import com.example.smartcity30.utils.SharedPreferencesUtil;
import com.google.gson.Gson;

import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalCenterPersonalInfoActivity extends AppCompatActivity {

    private static final String TAG = "PersonalCenterPersonalI";
    public String BASE_URL = "http://124.93.196.45:10001";
    public EditText et_user_info_userID;
    public EditText et_user_info_username;
    public EditText et_user_info_phone_number;
    public EditText et_user_info_email;
    public EditText et_user_info_id_card;
    public Button btn_user_info_save_info;
    public Button btn_user_info_change_info;
    public Button btn_user_info_clear_info;
    public RadioGroup rg_user_info_sex;
    public RadioButton rb_user_info_man_0;
    public RadioButton rb_user_info_woman_1;
    public Toolbar tb_user_info_top_bar;
    public String userName, email, phoneNum, sex, idCard, TOKEN;
    public String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_personal_info);
        getTokenBySPUtil();
        personalCenterNetworkRequest();
//        getUserInfoByIntent();

//        initView();
    }

    private void getTokenBySPUtil() {
        TOKEN = SharedPreferencesUtil.getString(getApplicationContext(), "TOKEN", "");
        Log.d(TAG, "getTokenBySPUtil: " + TOKEN);
    }

//    private void getUserInfoByIntent() {
//        Intent intent = getIntent();
//        userName = intent.getStringExtra("userName");
//        email = intent.getStringExtra("email");
//        phoneNum = intent.getStringExtra("phoneNum");
//        sex = intent.getStringExtra("sex");
//        idCard = intent.getStringExtra("idCard");
//        userId = intent.getStringExtra("userId");
//
//        Log.d(TAG, "getUserInfoByIntent: " + userId + userName + email + phoneNum + sex + idCard);
//    }

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
                        userId = String.valueOf(userInfoUserBeanData.getUserId());
                        initView();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetUserInfoResult> call, @NonNull Throwable throwable) {

            }
        });
    }

    private void initView() {
        tb_user_info_top_bar = findViewById(R.id.tb_user_info_top_bar);
        et_user_info_userID = findViewById(R.id.et_user_info_userID);
        et_user_info_username = findViewById(R.id.et_user_info_username);
        et_user_info_phone_number = findViewById(R.id.et_user_info_phone_number);
        et_user_info_email = findViewById(R.id.et_user_info_email);
        et_user_info_id_card = findViewById(R.id.et_user_info_id_card);
        rg_user_info_sex = findViewById(R.id.rg_user_info_sex);
        rb_user_info_man_0 = findViewById(R.id.rb_user_info_man_0);
        rb_user_info_woman_1 = findViewById(R.id.rb_user_info_woman_1);
        btn_user_info_save_info = findViewById(R.id.btn_user_info_save_info);
        btn_user_info_change_info = findViewById(R.id.btn_user_info_change_info);
        btn_user_info_clear_info = findViewById(R.id.btn_user_info_clear_info);

        tb_user_info_top_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_user_info_userID.setText(userId);
        et_user_info_username.setText(userName);
        et_user_info_phone_number.setText(phoneNum);
        et_user_info_email.setText(email);
        et_user_info_id_card.setText(idCard);

        if (Objects.equals(sex, "0")) {
            rb_user_info_man_0.setEnabled(true);
            rb_user_info_man_0.setChecked(true);
            rb_user_info_woman_1.setEnabled(false);
            rb_user_info_woman_1.setChecked(false);
        } else {
            rb_user_info_man_0.setEnabled(false);
            rb_user_info_man_0.setChecked(false);
            rb_user_info_woman_1.setEnabled(true);
            rb_user_info_woman_1.setChecked(true);
        }

        btn_user_info_change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_user_info_username.setEnabled(true);
                et_user_info_phone_number.setEnabled(true);
                et_user_info_email.setEnabled(true);
                et_user_info_id_card.setEnabled(true);
                rb_user_info_man_0.setEnabled(true);
                rb_user_info_woman_1.setEnabled(true);
                btn_user_info_change_info.setVisibility(View.GONE);
                btn_user_info_save_info.setVisibility(View.VISIBLE);
                btn_user_info_clear_info.setVisibility(View.VISIBLE);

            }
        });

        btn_user_info_save_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeUserInfoNetworkRequest();

                et_user_info_username.setEnabled(false);
                et_user_info_phone_number.setEnabled(false);
                et_user_info_email.setEnabled(false);
                et_user_info_id_card.setEnabled(false);

                if (Objects.equals(sex, "0")) {
                    rb_user_info_man_0.setEnabled(true);
                    rb_user_info_man_0.setChecked(true);
                    rb_user_info_woman_1.setEnabled(false);
                    rb_user_info_woman_1.setChecked(false);
                } else {
                    if (Objects.equals(sex, "1")) {
                        rb_user_info_man_0.setEnabled(false);
                        rb_user_info_man_0.setChecked(false);
                        rb_user_info_woman_1.setEnabled(true);
                        rb_user_info_woman_1.setChecked(true);
                    } else {
                        rb_user_info_man_0.setEnabled(false);
                        rb_user_info_man_0.setChecked(false);
                        rb_user_info_woman_1.setEnabled(false);
                        rb_user_info_woman_1.setChecked(false);
                    }

                }

                btn_user_info_change_info.setVisibility(View.VISIBLE);
                btn_user_info_save_info.setVisibility(View.GONE);
                btn_user_info_clear_info.setVisibility(View.GONE);
            }
        });

        btn_user_info_clear_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_user_info_username.setText(null);
                et_user_info_phone_number.setText(null);
                et_user_info_email.setText(null);
                et_user_info_id_card.setText(null);
                if (btn_user_info_save_info.getVisibility() == View.GONE) {
                    rb_user_info_man_0.setEnabled(false);
                    rb_user_info_woman_1.setEnabled(false);
                } else {
                    rb_user_info_man_0.setEnabled(true);
                    rb_user_info_woman_1.setEnabled(true);
                }
                rb_user_info_man_0.setChecked(false);
                rb_user_info_woman_1.setChecked(false);
            }
        });

    }

    private void changeUserInfoNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        userName = et_user_info_username.getText().toString();
        phoneNum = et_user_info_phone_number.getText().toString();
        if (rb_user_info_woman_1.isChecked()) {
            sex = "1";
        } else {
            sex = "0";
        }
        email = et_user_info_email.getText().toString();
        idCard = et_user_info_id_card.getText().toString();

        ChangeUserInfoInfo changeUserInfoInfo = new ChangeUserInfoInfo(userName, phoneNum, sex, email, idCard);
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(changeUserInfoInfo));
        Call<ChangeUserInfoResult> changeUserInfoResultCall = apiService.changeUserInfo(TOKEN, body);
        changeUserInfoResultCall.enqueue(new Callback<ChangeUserInfoResult>() {
            @Override
            public void onResponse(@NonNull Call<ChangeUserInfoResult> call, @NonNull Response<ChangeUserInfoResult> response) {
                if (response.code() == 200) {
                    ChangeUserInfoResult changeUserInfoResult = response.body();
                    assert changeUserInfoResult != null;
                    if (changeUserInfoResult.getCode() == 200) {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChangeUserInfoResult> call, @NonNull Throwable throwable) {

            }
        });
    }


}