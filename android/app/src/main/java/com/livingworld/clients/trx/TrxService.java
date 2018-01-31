package com.livingworld.clients.trx;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface TrxService {

    @POST("transaction/balance/month")
    Call<Response> getBalanceMonth();

    @GET("transaction/balance/day")
    Call<Response> getBalanceDay();


}
