package id.co.icg.lw.domain.merchant;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "merchant_id")
    private long merchantId;

    private String merchantName;
    private String merchantPhone;
    private String merchantImage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="merchant_category_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private MerchantCategory merchantCategory;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="merchantId", cascade = {CascadeType.ALL})
    private List<MerchantOfficeHour> merchantOfficeHourList;

    private String merchantLogo;

    @Column(columnDefinition = "TEXT")
    private String description;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public MerchantCategory getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(MerchantCategory merchantCategory) {
        this.merchantCategory = merchantCategory;
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

    @PrePersist
    protected void onCreate() {
        createAt = updateAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = new Date();
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MerchantOfficeHour> getMerchantOfficeHourList() {
        return merchantOfficeHourList;
    }

    public void setMerchantOfficeHourList(List<MerchantOfficeHour> merchantOfficeHourList) {
        this.merchantOfficeHourList = merchantOfficeHourList;
    }

    public String getMerchantImage() {
        return merchantImage;
    }

    public void setMerchantImage(String merchantImage) {
        this.merchantImage = merchantImage;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }
}
