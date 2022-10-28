package com.example.smartcity30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PersonalCenterPersonMyWalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_person_my_wallet);

        initView();
    }

    private void initView() {
        Toolbar tb_my_wallet_top_bar = findViewById(R.id.tb_my_wallet_top_bar);
        TextView tv_my_wallet_deposit_amount = findViewById(R.id.tv_my_wallet_deposit_amount);
        Button btn_my_wallet_recharge_logs = findViewById(R.id.btn_my_wallet_recharge_logs);
        Button btn_my_wallet_deposit_amount_50 = findViewById(R.id.btn_my_wallet_deposit_amount_50);
        Button btn_my_wallet_deposit_amount_100 = findViewById(R.id.btn_my_wallet_deposit_amount_100);
        Button btn_my_wallet_deposit_amount_200 = findViewById(R.id.btn_my_wallet_deposit_amount_200);
        Button btn_my_wallet_deposit_amount_500 = findViewById(R.id.btn_my_wallet_deposit_amount_500);
        Button btn_my_wallet_deposit_amount_1000 = findViewById(R.id.btn_my_wallet_deposit_amount_1000);
        EditText et_my_wallet_deposit_amount_other = findViewById(R.id.et_my_wallet_deposit_amount_other);
        RadioGroup rg_my_wallet_payment_methods = findViewById(R.id.rg_my_wallet_payment_methods);
        RadioButton rb_my_wallet_wechat_payment = findViewById(R.id.rb_my_wallet_wechat_payment);
        RadioButton rb_my_wallet_alipay_payment = findViewById(R.id.rb_my_wallet_alipay_payment);
        Button btn_my_wallet_confirm_recharge = findViewById(R.id.btn_my_wallet_confirm_recharge);

        tb_my_wallet_top_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_my_wallet_deposit_amount_50.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("50");
            }
        });
        btn_my_wallet_deposit_amount_100.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("100");
            }
        });
        btn_my_wallet_deposit_amount_200.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("200");
            }
        });
        btn_my_wallet_deposit_amount_500.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("500");
            }
        });
        btn_my_wallet_deposit_amount_1000.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("1000");
            }
        });
    }

}