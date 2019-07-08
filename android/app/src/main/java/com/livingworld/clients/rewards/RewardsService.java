package com.livingworld.clients.rewards;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface RewardsService {

    @GET("event")
    Call<Response> getCurrentEvent();

    @GET("reward")
    Call<Response> getRedeemedReward();

    @POST("reward/redeem")
    Call<Response> redeem(@Body Map<String, String> map);

    @GET("reward/list")
    Call<Response> getRewardList();

}
