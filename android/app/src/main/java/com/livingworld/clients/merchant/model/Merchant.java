package com.livingworld.clients.merchant.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class Merchant implements Serializable{

    private String merchantId;
    private String merchantName;
    private String merchantPhone;
    private String merchantImage;
    private MerchantCategory merchantCategory;
    private String merchantLogo;
    private String description;
    private Date createAt;
    private Date updateAt;
    private List<MerchantOfficeHour> merchantOfficeHourList;

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

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public String getMerchantImage() {
        return merchantImage;
    }

    public void setMerchantImage(String merchantImage) {
        this.merchantImage = merchantImage;
    }

    public MerchantCategory getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(MerchantCategory merchantCategory) {
        this.merchantCategory = merchantCategory;
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

    public List<MerchantOfficeHour> getMerchantOfficeHourList() {
        return merchantOfficeHourList;
    }

    public void setMerchantOfficeHourList(List<MerchantOfficeHour> merchantOfficeHourList) {
        this.merchantOfficeHourList = merchantOfficeHourList;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", merchantPhone='" + merchantPhone + '\'' +
                ", merchantImage='" + merchantImage + '\'' +
                ", merchantCategory=" + merchantCategory +
                ", merchantLogo='" + merchantLogo + '\'' +
                ", description='" + description + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", merchantOfficeHourList=" + merchantOfficeHourList +
                '}';
    }
}
