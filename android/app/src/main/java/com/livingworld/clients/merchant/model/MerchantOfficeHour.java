package com.livingworld.clients.merchant.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ndoenks on 02/06/18.
 */

public class MerchantOfficeHour implements Serializable{

    private long merchantWorkingHoursId;
    private Merchant merchantId;
    private int day;
    private String startTime;
    private String endTime;
    private Date createAt;
    private Date updateAt;

    public long getMerchantWorkingHoursId() {
        return merchantWorkingHoursId;
    }

    public void setMerchantWorkingHoursId(long merchantWorkingHoursId) {
        this.merchantWorkingHoursId = merchantWorkingHoursId;
    }

    public Merchant getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Merchant merchantId) {
        this.merchantId = merchantId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
