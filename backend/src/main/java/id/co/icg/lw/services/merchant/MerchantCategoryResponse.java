package id.co.icg.lw.services.merchant;

import id.co.icg.lw.domain.merchant.MerchantCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MerchantCategoryResponse {
    private String merchantCategoryId;
    private String merchantCategoryName;
    private List<MerchantResponse> merchantList;

    public MerchantCategoryResponse(){}

    public MerchantCategoryResponse(MerchantCategory merchantCategory) {
        this.merchantCategoryId = merchantCategory.getMerchantCategoryId();
        this.merchantCategoryName = merchantCategory.getMerchantCategoryName();

        if (merchantCategory.getMerchantList() != null) {
            merchantList = new ArrayList<>();
            for (id.co.icg.lw.domain.merchant.Merchant merchant : merchantCategory.getMerchantList()) {
                merchantList.add(new MerchantResponse(merchant));
            }
        }
    }

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

    public List<MerchantResponse> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<MerchantResponse> merchantList) {
        this.merchantList = merchantList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantCategoryResponse that = (MerchantCategoryResponse) o;
        return Objects.equals(merchantCategoryId, that.merchantCategoryId) &&
                Objects.equals(merchantCategoryName, that.merchantCategoryName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(merchantCategoryId, merchantCategoryName);
    }
}
