package com.livingworld.clients.merchant.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class MerchantCategory implements Serializable {

    String merchantCategoryId;
    String merchantCategoryName;
    List<Merchant> merchantList;

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
}
