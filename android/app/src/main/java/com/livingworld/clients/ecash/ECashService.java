package com.livingworld.clients.ecash;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ECashService {
    @POST("e-cash/validate")
    Call<Response> validate();

    @POST("e-cash/ticket")
    Call<Response> ticket();
}
