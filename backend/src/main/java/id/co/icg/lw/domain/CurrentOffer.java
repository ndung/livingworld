package id.co.icg.lw.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class CurrentOffer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "current_offer_id")
    private long currentOfferId;

    private String title;

    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="currentOffer", cascade = {CascadeType.ALL})
    private List<CurrentOfferImage> currentOfferImages;

    private Date startDate;
    private Date endDate;

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

