package id.co.icg.lw.domain.merchant;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="merchant_category")
public class MerchantCategory {

    @Id
    @Column(name = "merchant_category_id")
    private String merchantCategoryId;

    @Column(name = "merchant_category_name")
    private String merchantCategoryName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="merchantCategory", cascade = {CascadeType.ALL})
    private List<Merchant> merchantList;

    public String getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setMerchantCategoryId(String merchantCategoryId) {
        this.merchantCategoryId = merchantCategoryId;
    }

    public String getMerchantCategoryName() {
        return merchantCategoryName;
    }

    public void setMerchantCategoryName(String merchantCategoryName) {
        this.merchantCategoryName = merchantCategoryName;
    }

    public List<Merchant> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<Merchant> merchantList) {
        this.merchantList = merchantList;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", updatable = false)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAt;

    @PrePersist
    protected void onCreate() {
        createAt = updateAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = new Date();
    }

    @Override
    public String toString() {
        return "MerchantCategory{" +
                "merchantCategoryId='" + merchantCategoryId + '\'' +
                ", merchantCategoryName='" + merchantCategoryName + '\'' +
                '}';
    }
}
