package id.co.icg.lw.services.reward;

import java.util.List;

public interface RewardService {
    List<RewardResponse> getRewards();
    long getPoint(String userId);
    long redeemPoint(String userId, RedeemRequest redeemRequest);
}
