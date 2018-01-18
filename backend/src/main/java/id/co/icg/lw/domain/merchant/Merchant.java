package id.co.icg.lw.domain.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

public class Merchant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long merchantId;

    private String merchantName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="merchant_category_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private MerchantCategory merchantCategory;

    @Column(columnDefinition= "TIME WITH TIME ZONE")
    private Date createAt;

    @Column(columnDefinition= "TIME WITH TIME ZONE")
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
}
