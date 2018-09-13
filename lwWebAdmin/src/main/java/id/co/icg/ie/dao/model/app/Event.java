package id.co.icg.ie.dao.model.app;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String image;

    @Id
    @Column(name="event_id", unique=true, nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name", nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description", nullable=false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Column(name="image", nullable=false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
