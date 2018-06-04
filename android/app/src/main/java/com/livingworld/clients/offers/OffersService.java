package com.livingworld.clients.offers;

import com.livingworld.clients.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by ndoenks on 04/06/18.
 */

public interface OffersService {

    @GET("current-offer")
    Call<Response> getCurrentOffers();

}
