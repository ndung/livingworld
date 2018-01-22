package id.co.icg.lw.services.reward;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RewardServiceBean implements RewardService {

    @Override
    public List<RewardResponse> getRewards() {
        return new ArrayList<>();
    }

    @Override
    public long getPoint(String userId) {
        return 0;
    }

    @Override
    public long redeemPoint(String userId, RedeemRequest redeemRequest) {
        return 0;
    }
}
