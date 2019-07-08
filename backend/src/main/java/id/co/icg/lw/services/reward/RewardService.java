package id.co.icg.lw.services.reward;

import id.co.icg.lw.domain.Redeem;
import id.co.icg.lw.domain.Reward;

import java.util.List;

public interface RewardService {
    List<Reward> getRewardList(String userId);
    List<Redeem> getRewards(String userId);
    long redeemPoint(String userId, RedeemRequest redeemRequest);
}
