package com.livingworld.clients.merchant.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class Merchant {

    @SerializedName("merchantCategoryId")
    private String merchantId;
    private String merchantName;
    private String merchantLogo;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }
}
