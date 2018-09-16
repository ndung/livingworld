package com.livingworld.clients.member;

import com.livingworld.clients.model.Response;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface MemberService {

//    @FormUrlEncoded
    @POST("member/registration")
    Call<Response> registerMember(@Body Map<String, String> map);

    @PUT("user/edit")
    Call<Response> updateMember(@Body Map<String, String> map);

    @POST("user/refresh")
    Call<Response> refreshUser();

    @POST("user/registration")
    Call<Response> registerUser(@Body Map<String, String> map);

    @Multipart
    @POST("user/upload/photo-profile")
    Call<Response> upload(@Part MultipartBody.Part image);

    @GET("files/{fileName}")
    Call<Response> getFile(@Query("{fileName}") String filename);

}
