package com.livingworld.clients.rewards.model;

import java.io.Serializable;

public class Redeem implements Serializable{
    private long redeemId;
    private String code;

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
}
