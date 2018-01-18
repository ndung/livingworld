package id.co.icg.lw.domain.merchant;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="merchant_category")
public class MerchantCategory {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "merchant_category_id")
    private long merchantCategoryId;

    private String merchantCategoryName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="merchantCategory", cascade = {CascadeType.ALL})
    private List<Merchant> merchantList;

    public long getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setMerchantCategoryId(long merchantCategoryId) {
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

}
