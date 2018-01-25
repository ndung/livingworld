package com.livingworld.clients.inbox;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface InboxService {

    @GET //("/message/page")
    Call<Response> getMessage(@Url String url);

//    @FormUrlEncoded
    @POST("message/contact-us")
    Call<Response> contacUs(@Body Map<String, String> map);

}
