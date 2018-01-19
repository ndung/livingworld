package id.co.icg.lw.services.merchant;

import java.util.List;

public class MerchantCategoryResponse {
    private int merchantCategoryId;
    private String merchantCategoryName;
    private List<MerchantResponse> merchantList;

    public int getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setMerchantCategoryId(int merchantCategoryId) {
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
}
