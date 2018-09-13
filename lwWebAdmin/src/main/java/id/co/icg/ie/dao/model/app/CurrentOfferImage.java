package id.co.icg.ie.dao.model.app;

import id.co.icg.ie.dao.util.PojoModel;

import javax.persistence.*;

@Entity
@Table(name = "current_offer_image")
public class CurrentOfferImage extends PojoModel {

    private String id;
    private CurrentOffer currentOffer;

    @Id
    @Column(name="current_offer_image_id", unique=true, nullable=false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="current_offer_id")
    public CurrentOffer getCurrentOffer() {
        return currentOffer;
    }

    public void setCurrentOffer(CurrentOffer currentOffer) {
        this.currentOffer = currentOffer;
    }
}
