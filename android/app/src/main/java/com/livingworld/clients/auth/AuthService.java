package com.livingworld.clients.auth;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface AuthService {

    @FormUrlEncoded
    @POST("/user/check/card-number")
    Call<Response> checkCard(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/user/sign-in")
    Call<Response> signIn(@FieldMap Map<String, String> map);

}
