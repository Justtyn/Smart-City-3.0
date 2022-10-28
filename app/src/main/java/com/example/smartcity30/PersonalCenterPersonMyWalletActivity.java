package com.example.smartcity30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity30.bean.WalletRechargeResult;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalCenterPersonMyWalletActivity extends AppCompatActivity {

    public String customAmount;
    public String BASE_URL = "http://124.93.196.45:10001";
    public static final String TAG = "PersonalCenterPersonMyW";
    public Toolbar tb_my_wallet_top_bar;
    public TextView tv_my_wallet_deposit_amount;
    public Button btn_my_wallet_recharge_logs;
    public Button btn_my_wallet_deposit_amount_50;
    public Button btn_my_wallet_deposit_amount_100;
    public Button btn_my_wallet_deposit_amount_200;
    public Button btn_my_wallet_deposit_amount_500;
    public Button btn_my_wallet_deposit_amount_1000;
    public EditText et_my_wallet_deposit_amount_other;
    public RadioGroup rg_my_wallet_payment_methods;
    public RadioButton rb_my_wallet_wechat_payment;
    public RadioButton rb_my_wallet_alipay_payment;
    public Button btn_my_wallet_confirm_recharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除app标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_person_my_wallet);

        initView();
    }

    private void initView() {
        tb_my_wallet_top_bar = findViewById(R.id.tb_my_wallet_top_bar);
        tv_my_wallet_deposit_amount = findViewById(R.id.tv_my_wallet_deposit_amount);
        btn_my_wallet_recharge_logs = findViewById(R.id.btn_my_wallet_recharge_logs);
        btn_my_wallet_deposit_amount_50 = findViewById(R.id.btn_my_wallet_deposit_amount_50);
        btn_my_wallet_deposit_amount_100 = findViewById(R.id.btn_my_wallet_deposit_amount_100);
        btn_my_wallet_deposit_amount_200 = findViewById(R.id.btn_my_wallet_deposit_amount_200);
        btn_my_wallet_deposit_amount_500 = findViewById(R.id.btn_my_wallet_deposit_amount_500);
        btn_my_wallet_deposit_amount_1000 = findViewById(R.id.btn_my_wallet_deposit_amount_1000);
        et_my_wallet_deposit_amount_other = findViewById(R.id.et_my_wallet_deposit_amount_other);
        rg_my_wallet_payment_methods = findViewById(R.id.rg_my_wallet_payment_methods);
        rb_my_wallet_wechat_payment = findViewById(R.id.rb_my_wallet_wechat_payment);
        rb_my_wallet_alipay_payment = findViewById(R.id.rb_my_wallet_alipay_payment);
        btn_my_wallet_confirm_recharge = findViewById(R.id.btn_my_wallet_confirm_recharge);

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
                customAmount = "50";
            }
        });
        btn_my_wallet_deposit_amount_100.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("100");
                customAmount = "100";
            }
        });
        btn_my_wallet_deposit_amount_200.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("200");
                customAmount = "200";
            }
        });
        btn_my_wallet_deposit_amount_500.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("500");
                customAmount = "500";
            }
        });
        btn_my_wallet_deposit_amount_1000.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                tv_my_wallet_deposit_amount.setText("1000");
                customAmount = "1000";
            }
        });

//        et_my_wallet_deposit_amount_other.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                customAmount = Integer.parseInt(charSequence.toString());
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                customAmount = Integer.parseInt(charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        // 软键盘右下角键监听

        et_my_wallet_deposit_amount_other.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    customAmount = et_my_wallet_deposit_amount_other.getText().toString();
                    tv_my_wallet_deposit_amount.setText(customAmount);
                    et_my_wallet_deposit_amount_other.setText("");
                    return false;
                }
                return false;
            }
        });

        btn_my_wallet_confirm_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb_my_wallet_wechat_payment.isChecked()) {
                    Log.d(TAG, "onClick: " + "微信支付");
                    walletRechargeNetworkRequest("微信");
                }
                if (rb_my_wallet_alipay_payment.isChecked()) {
                    Log.d(TAG, "onClick: " + "支付宝支付");
                    walletRechargeNetworkRequest("支付宝");
                }
            }
        });
    }

    private void walletRechargeNetworkRequest(String payType) {
        Log.d(TAG, "walletRechargeNetworkRequest: " + "Network Request");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<WalletRechargeResult> walletRechargeResultCall = apiService.walletRecharge(Integer.parseInt(customAmount), payType);
        walletRechargeResultCall.enqueue(new Callback<WalletRechargeResult>() {
            @Override
            public void onResponse(@NonNull Call<WalletRechargeResult> call, @NonNull Response<WalletRechargeResult> response) {

                WalletRechargeResult walletRechargeResult = response.body();
                if (Objects.requireNonNull(walletRechargeResult).getCode() == 200) {
                    Log.d(TAG, "onResponse: " + "Recharge Success");
                    Toast.makeText(getApplicationContext(), "充值成功", Toast.LENGTH_SHORT).show();
                    tv_my_wallet_deposit_amount.setText("");
                    et_my_wallet_deposit_amount_other.setText("");
                    rb_my_wallet_wechat_payment.setChecked(false);
                    rb_my_wallet_alipay_payment.setChecked(false);
                }

            }

            @Override
            public void onFailure(@NonNull Call<WalletRechargeResult> call, @NonNull Throwable throwable) {

            }
        });
    }

}