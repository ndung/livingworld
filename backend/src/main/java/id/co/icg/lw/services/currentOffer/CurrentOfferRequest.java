package id.co.icg.lw.services.currentOffer;

import id.co.icg.lw.domain.CurrentOffer;

public class CurrentOfferRequest {
    private long id;
    private String title;
    private String shortDescription;
    private String thumbnail;

    public CurrentOfferRequest() {}

    public CurrentOfferRequest(CurrentOffer currentOffer) {
        this.id = currentOffer.getCurrentOfferId();
        this.title = currentOffer.getTitle();
        this.shortDescription = currentOffer.getShortDescription();
        this.thumbnail = currentOffer.getCurrentOfferImages().get(0).getCurrentOfferImageId();
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
