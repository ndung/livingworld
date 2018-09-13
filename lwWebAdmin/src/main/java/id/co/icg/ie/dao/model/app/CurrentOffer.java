package id.co.icg.ie.dao.model.app;

import id.co.icg.ie.dao.util.PojoModel;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "current_offer")
public class CurrentOffer extends PojoModel {
    private Long    id;
    private String  title;
    private String  shortDescription;
    private String  longDescription;
    private Date startDate;
    private Date endDate;

    public CurrentOffer() {
    }

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="current_offer_id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="title", nullable=false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="short_description", nullable=false)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Column(name="description", nullable=false)
    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
