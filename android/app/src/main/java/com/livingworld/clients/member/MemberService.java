package com.livingworld.clients.member;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface MemberService {

    @FormUrlEncoded
    @POST("/user/registration/member")
    Call<Response> registerMember(@FieldMap Map<String, String> map);

}
