package com.livingworld.clients.merchant.model;

import java.util.List;
import java.util.Objects;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class MerchantDetail {

    private String merchantId;
    private String merchantName;
    private String merchantImage;
    private String merchantPhone;
    private String merchantDescription;
    private List<MerchantOfficeHour> merchantOfficeHour;

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

    public String getMerchantDescription() {
        return merchantDescription;
    }

    public void setMerchantDescription(String merchantDescription) {
        this.merchantDescription = merchantDescription;
    }

    public List<MerchantOfficeHour> getMerchantOfficeHour() {
        return merchantOfficeHour;
    }

    public void setMerchantOfficeHour(List<MerchantOfficeHour> merchantOfficeHour) {
        this.merchantOfficeHour = merchantOfficeHour;
    }

    public class MerchantOfficeHour {
        int day;
        String open;
        String close;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getClose() {
            return close;
        }

        public void setClose(String close) {
            this.close = close;
        }
    }
}
