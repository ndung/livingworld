package com.livingworld.clients.offers.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ndoenks on 04/06/18.
 */

public class CurrentOffer implements Serializable{

    private long currentOfferId;
    private String title;
    private String shortDescription;
    private String description;
    private List<CurrentOfferImage> currentOfferImages;

    private Date startDate;
    private Date endDate;

    public long getCurrentOfferId() {
        return currentOfferId;
    }

    public void setCurrentOfferId(long currentOfferId) {
        this.currentOfferId = currentOfferId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CurrentOfferImage> getCurrentOfferImages() {
        return currentOfferImages;
    }

    public void setCurrentOfferImages(List<CurrentOfferImage> currentOfferImages) {
        this.currentOfferImages = currentOfferImages;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
