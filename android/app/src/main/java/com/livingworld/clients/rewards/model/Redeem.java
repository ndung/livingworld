package com.livingworld.clients.rewards.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Redeem implements Serializable{
    private long redeemId;
    private String code;
    private List<RedeemedReward> redeemedRewards;
    private Date createAt;
    private Date expiredDate;

    public long getRedeemId() {
        return redeemId;
    }

    public void setRedeemId(long redeemId) {
        this.redeemId = redeemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RedeemedReward> getRedeemedRewards() {
        return redeemedRewards;
    }

    public void setRedeemedRewards(List<RedeemedReward> redeemedRewards) {
        this.redeemedRewards = redeemedRewards;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
