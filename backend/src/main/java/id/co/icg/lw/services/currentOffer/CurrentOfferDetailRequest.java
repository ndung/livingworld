package id.co.icg.lw.services.currentOffer;

import id.co.icg.lw.domain.CurrentOffer;
import id.co.icg.lw.domain.CurrentOfferImage;

import java.text.SimpleDateFormat;

public class CurrentOfferDetailRequest {
    private long id;
    private String title;
    private String[] images;
    private String startDate;
    private String endDate;
    private String description;

    public CurrentOfferDetailRequest(CurrentOffer currentOffer) {
        this.id = currentOffer.getCurrentOfferId();
        this.title = currentOffer.getTitle();
        this.description = currentOffer.getDescription();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.startDate = sdf.format(currentOffer.getStartDate());
        this.endDate = sdf.format(currentOffer.getEndDate());
        this.images = new String[currentOffer.getCurrentOfferImages().size()];

        for (int i = 0; i < images.length; i++) {
            images[i] = currentOffer.getCurrentOfferImages().get(i).getCurrentOfferImageId();
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
