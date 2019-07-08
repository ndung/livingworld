package com.livingworld.clients.rewards.model;

import java.util.Date;

public class RedeemedReward {
    private long id;
    private int quantity;
    private int status;
    private Reward rewardId;
    private Date createAt;
    private Date updateAt;
    private int rewardPoint;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Reward getRewardId() {
        return rewardId;
    }

    public void setRewardId(Reward rewardId) {
        this.rewardId = rewardId;
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

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }
}
