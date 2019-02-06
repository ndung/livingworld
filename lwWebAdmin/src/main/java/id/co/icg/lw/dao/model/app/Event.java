package id.co.icg.lw.dao.model.app;

import id.co.icg.lw.dao.util.PojoModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "event")
public class Event extends PojoModel {

    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String image;
    private Date createAt;
    private Date updateAt;
    private String active;

    @Id
    @GeneratedValue(strategy=IDENTITY)
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


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", updatable = false)
    public Date getCreateAt() { return createAt; }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Column(name="active", nullable=false)
    public String getActive() { return active; }

    public void setActive(String active) { this.active = active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
