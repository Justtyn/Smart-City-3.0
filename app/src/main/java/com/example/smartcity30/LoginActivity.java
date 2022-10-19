package com.example.smartcity30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public EditText et_login_username;
    public EditText et_login_password;
    public Button btn_login_login;
    public Button btn_login_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏设置
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        btn_login_login = findViewById(R.id.btn_login_login);
        btn_login_register = findViewById(R.id.btn_login_register);

        btn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}