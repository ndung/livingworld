package com.livingworld.clients.rewards.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class Rewards {

    @SerializedName("rewardId")
    int rewardId;
    @SerializedName("rewardName")
    String rewardName;
    @SerializedName("rewardPoint")
    String rewardPoint;
    @SerializedName("rewardImage")
    String rewardImage;

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(String rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getRewardImage() {
        return rewardImage;
    }

    public void setRewardImage(String rewardImage) {
        this.rewardImage = rewardImage;
    }
}
