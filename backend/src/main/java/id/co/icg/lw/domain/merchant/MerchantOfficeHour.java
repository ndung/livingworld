package id.co.icg.lw.domain.merchant;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MerchantOfficeHour {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "merchant_working_id")
    private long merchantWorkingHoursId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="merchant_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Merchant merchantId;

    private int day;

    private String startTime;

    private String endTime;

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

    public long getMerchantWorkingHoursId() {
        return merchantWorkingHoursId;
    }

    public void setMerchantWorkingHoursId(long merchantWorkingHoursId) {
        this.merchantWorkingHoursId = merchantWorkingHoursId;
    }

    public Merchant getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Merchant merchantId) {
        this.merchantId = merchantId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
