package com.example.smartcity30.bean;

import com.google.gson.annotations.SerializedName;

public class WalletRechargeResult {


    /**
     * msg : 操作成功
     * code : 200
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
