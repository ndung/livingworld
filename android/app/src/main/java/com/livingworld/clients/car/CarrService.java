package com.livingworld.clients.car;

import com.livingworld.clients.model.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Dizzay on 11/10/2017.
 */

public interface CarrService {

    @GET("vehicle")
    Call<Response> getVehicle();

    @POST("vehicle/create")
    Call<Response> createVehicle(@Body   Map<String, String> map);

    @DELETE("vehicle/{vehicleId}")
    Call<Response> deleteVehicle(@Query("vehicleId") String id);

    @POST("vehicle/edit")
    Call<Response> editVehicle(@Body Map<String, String> map);


}
