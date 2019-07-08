package id.co.icg.lw.services.reward;

import id.co.icg.lw.domain.Redeem;
import id.co.icg.lw.domain.RedeemedReward;

import java.util.List;
import java.util.Map;

public class RewardResponse {
    private Map<Redeem, List<RedeemedReward>> objects;

    public Map<Redeem, List<RedeemedReward>> getObjects() {
        return objects;
    }

    public void setObjects(Map<Redeem, List<RedeemedReward>> objects) {
        this.objects = objects;
    }
}
