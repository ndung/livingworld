package com.livingworld.clients.offers.model;

import java.io.Serializable;

/**
 * Created by ndoenks on 04/06/18.
 */

public class CurrentOfferImage implements Serializable{

    private String currentOfferImageId;

    public String getCurrentOfferImageId() {
        return currentOfferImageId;
    }

    public void setCurrentOfferImageId(String currentOfferImageId) {
        this.currentOfferImageId = currentOfferImageId;
    }
}
