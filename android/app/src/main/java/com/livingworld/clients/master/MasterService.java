package com.livingworld.clients.master;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface MasterService {

    @GET("/master")
    Call<Response> getMaster(@Query("type") String type);

}
