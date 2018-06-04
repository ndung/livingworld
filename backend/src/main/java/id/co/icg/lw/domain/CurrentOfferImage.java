package id.co.icg.lw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CurrentOfferImage {
    @Id
    @Column(name = "current_offer_image_id")
    private String currentOfferImageId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_offer_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private CurrentOffer currentOffer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @PrePersist
    protected void onCreate() {
        createAt = updateAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = new Date();
    }

    public String getCurrentOfferImageId() {
        return currentOfferImageId;
    }

    public void setCurrentOfferImageId(String currentOfferImageId) {
        this.currentOfferImageId = currentOfferImageId;
    }

    public CurrentOffer getCurrentOffer() {
        return currentOffer;
    }

    public void setCurrentOffer(CurrentOffer currentOffer) {
        this.currentOffer = currentOffer;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}