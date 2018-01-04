package com.livingworld.clients.login;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface LoginService {

    @FormUrlEncoded
    @POST("login/sales")
    Call<Response> login(@FieldMap Map<String, String> map);

}
