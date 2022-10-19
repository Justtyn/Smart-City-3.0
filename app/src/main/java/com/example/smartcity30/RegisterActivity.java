package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity30.bean.RegisterInfo;
import com.example.smartcity30.bean.RegisterResult;
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

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    public String BASE_URL = "http://124.93.196.45:10001";
    public String userName, passWord, phoneNumber, sex, registerRequestMsg;

    public TextView tv_register_back;
    public EditText et_register_username;
    public EditText et_register_password;
    public EditText et_register_phone_number;
    public RadioGroup rg_register_sex;
    public RadioButton rb_register_man_0;
    public RadioButton rb_register_woman_1;
    public Button btn_register_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏设置
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    @SuppressLint("NonConstantResourceId")
    private void initView() {
        tv_register_back = findViewById(R.id.tv_register_back);
        et_register_username = findViewById(R.id.et_register_username);
        et_register_password = findViewById(R.id.et_register_password);
        et_register_phone_number = findViewById(R.id.et_register_phone_number);
        rg_register_sex = findViewById(R.id.rg_register_sex);
        rb_register_man_0 = findViewById(R.id.rb_register_man_0);
        rb_register_woman_1 = findViewById(R.id.rb_register_woman_1);
        btn_register_submit = findViewById(R.id.btn_register_submit);

        tv_register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_register_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                userName = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_register_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                passWord = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passWord = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_register_phone_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                phoneNumber = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneNumber = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rg_register_sex.clearCheck();
        // 获取选中的 RadioButton
        rg_register_sex.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_register_man_0:
                    sex = "0";
                    Log.d(TAG, "onCheckedChanged: " + 0);
                    break;
                case R.id.rb_register_woman_1:
                    sex = "1";
                    Log.d(TAG, "onCheckedChanged: " + 1);
                    break;
            }

            registerNetworkRequests();
        });

        btn_register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(registerRequestMsg, "操作成功")) {
                    Toast.makeText(getApplicationContext(), "注册成功，请登录", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), registerRequestMsg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerNetworkRequests() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        RegisterInfo registerInfo = new RegisterInfo(userName, passWord, phoneNumber, sex);
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(registerInfo));

        Call<RegisterResult> registerResultCall = apiService.register(body);
        registerResultCall.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResult> call, @NonNull Response<RegisterResult> response) {
                if (response.code() == 200) {
                    RegisterResult registerResultBody = response.body();
                    if (registerResultBody != null) {
                        registerRequestMsg = registerResultBody.getMsg();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResult> call, @NonNull Throwable throwable) {

            }
        });
    }

}