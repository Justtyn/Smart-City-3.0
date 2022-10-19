package com.example.smartcity30;

import com.example.smartcity30.bean.LoginResult;
import com.example.smartcity30.bean.RegisterResult;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/prod-api/api/register")
    Call<RegisterResult> register(@Body RequestBody body);

    @POST("/prod-api/api/login")
    Call<LoginResult> login(@Body RequestBody body);


}
