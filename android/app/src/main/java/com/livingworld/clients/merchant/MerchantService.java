package com.livingworld.clients.merchant;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface MerchantService {

    @GET("/merchant")
    Call<Response> getMerchant();

//    @FormUrlEncoded
    @GET
    Call<Response> contacUs(@Url String url);

}
