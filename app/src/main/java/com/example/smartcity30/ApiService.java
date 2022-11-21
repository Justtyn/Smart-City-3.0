package com.example.smartcity30;

import com.example.smartcity30.bean.AmountsChangesInfoResult;
import com.example.smartcity30.bean.ChangeUserInfoResult;
import com.example.smartcity30.bean.GetAllServiceResult;
import com.example.smartcity30.bean.GetUserInfoResult;
import com.example.smartcity30.bean.HomeFragmentBannerInfoResult;
import com.example.smartcity30.bean.NewsDetailsResult;
import com.example.smartcity30.bean.LoginResult;
import com.example.smartcity30.bean.NewsCategoryResult;
import com.example.smartcity30.bean.ParkListInfoResult;
import com.example.smartcity30.bean.ParkListParkingRecordsResult;
import com.example.smartcity30.bean.RegisterResult;
import com.example.smartcity30.bean.WalletRechargeResult;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/prod-api/api/register")
    Call<RegisterResult> register(@Body RequestBody body);

    @POST("/prod-api/api/login")
    Call<LoginResult> login(@Body RequestBody body);

    @GET("/prod-api/api/service/list")
    Call<GetAllServiceResult> getAllService();

    @GET("/prod-api/press/category/list")
    Call<NewsCategoryResult> getNewsCategory();

    @GET("/prod-api/press/press/list")
    Call<NewsDetailsResult> getNewsDetailsByType(@Query("type") int type);

    @GET("/prod-api/press/press/list")
    Call<NewsDetailsResult> getNewsDetails();

    @GET("/prod-api/api/rotation/list")
    Call<HomeFragmentBannerInfoResult> getHomeFragmentBannerInfo(@Query("type") int type);

    @GET("/prod-api/api/common/user/getInfo")
    Call<GetUserInfoResult> getUserInfo(@Header("Authorization") String TOKEN);

    @PUT("/prod-api/api/common/user")
    Call<ChangeUserInfoResult> changeUserInfo(@Header("Authorization") String TOKEN, @Body RequestBody body);

    @GET("/prod-api/api/park/lot/list")
    Call<ParkListInfoResult> getParkListInfo();

    @POST("/prod-api/api/park/recharge/pay")
    Call<WalletRechargeResult> walletRecharge(@Header("Authorization") String TOKEN, @Query("money") int money);

    @GET("/prod-api/api/common/balance/list")
    Call<AmountsChangesInfoResult> amountsChangesInfo(@Header("Authorization") String TOKEN);

    @GET("/prod-api/api/park/lot/record/list")
    Call<ParkListParkingRecordsResult> getParkingRecordsInfo(@Query("parkName") String parkName);
}