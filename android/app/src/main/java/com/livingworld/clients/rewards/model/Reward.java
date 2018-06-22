package com.livingworld.clients.rewards.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.livingworld.clients.merchant.model.Merchant;

import java.io.Serializable;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class Reward implements Serializable, Comparable<Reward>{

    Long rewardId;
    String rewardName;
    String rewardPoint;
    String rewardImage;
    String rewardDescription;
    private Merchant merchant;

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
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

    public String getRewardDescription() { return rewardDescription; }

    public void setRewardDescription(String rewardDescription) { this.rewardDescription = rewardDescription; }

    public Merchant getMerchant() { return merchant; }

    public void setMerchant(Merchant merchant) { this.merchant = merchant; }

    @Override
    public String toString() {
        return "Reward{" +
                "rewardId=" + rewardId +
                ", rewardName='" + rewardName + '\'' +
                ", rewardPoint='" + rewardPoint + '\'' +
                ", rewardImage='" + rewardImage + '\'' +
                ", merchant=" + merchant +
                '}';
    }

    @Override
    public int compareTo(@NonNull Reward o) {
        int last = this.rewardId.compareTo(o.rewardId);
        return last == 0 ? this.rewardId.compareTo(o.rewardId) : last;
    }
}
