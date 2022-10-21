package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartcity30.bean.LoginInfo;
import com.example.smartcity30.bean.LoginResult;
import com.example.smartcity30.bean.RegisterResult;
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

public class LoginActivity extends AppCompatActivity {

    public EditText et_login_username;
    public EditText et_login_password;
    public Button btn_login_login;
    public Button btn_login_register;
    public CheckBox ckb_login_save_info;
    public String userName, passWord, TOKEN, loginRequestMsg;

//    public String userName = "jzh";
//    public String passWord = "123456";
//    public String TOKEN, loginRequestMsg;

    public int loginRequestCode;
    public String BASE_URL = "http://124.93.196.45:10001";
    public String userNameFromSP;
    public String passWordFromSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏设置
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getUserInfoBySPUtil();
        initView();
    }


    private void initView() {
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        ckb_login_save_info = findViewById(R.id.ckb_login_save_info);
        btn_login_login = findViewById(R.id.btn_login_login);
        btn_login_register = findViewById(R.id.btn_login_register);

        et_login_username.setText(userNameFromSP);
        et_login_password.setText(passWordFromSP);

        et_login_username.addTextChangedListener(new TextWatcher() {
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
        et_login_password.addTextChangedListener(new TextWatcher() {
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

        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName != null && passWord != null) {
                    loginNetworkRequest();
                } else {
                    userName = et_login_username.getText().toString();
                    passWord = et_login_password.getText().toString();
                    loginNetworkRequest();
                    if (userName == null) {
                        Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    }
                    if (passWord == null) {
                        Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveUserInfoBySPUtil() {
        SharedPreferencesUtil.putString(getApplicationContext(), "userName", userName);
        SharedPreferencesUtil.putString(getApplicationContext(), "passWord", passWord);
    }

    private void getUserInfoBySPUtil() {
        userNameFromSP = SharedPreferencesUtil.getString(getApplicationContext(), "userName", "");
        passWordFromSP = SharedPreferencesUtil.getString(getApplicationContext(), "passWord", "");
    }

    private void loginNetworkRequest() {
        if (ckb_login_save_info.isChecked()) {
            saveUserInfoBySPUtil();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        LoginInfo loginInfo = new LoginInfo(userName, passWord);
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(loginInfo));

        Call<LoginResult> loginResultCall = apiService.login(body);
        loginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(@NonNull Call<LoginResult> call, @NonNull Response<LoginResult> response) {
                if (response.code() == 200) {
                    LoginResult loginResult = response.body();
                    if (loginResult != null) {
                        loginRequestCode = loginResult.getCode();
                        loginRequestMsg = loginResult.getMsg();
                        if (loginRequestCode == 200) {
                            TOKEN = loginResult.getToken();
                            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("TOKEN", TOKEN);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), loginRequestMsg, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResult> call, @NonNull Throwable throwable) {

            }
        });
    }


}