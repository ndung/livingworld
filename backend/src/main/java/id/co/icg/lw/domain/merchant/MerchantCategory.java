package id.co.icg.lw.domain.merchant;

import javax.persistence.*;
import java.util.List;

@Entity
public class MerchantCategory {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long merchantCategoryId;

    private String merchantCategoryName;

    @OneToMany(mappedBy="merchant")
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
}
