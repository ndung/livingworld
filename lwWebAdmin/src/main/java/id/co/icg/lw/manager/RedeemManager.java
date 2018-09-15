package id.co.icg.lw.manager;

import id.co.icg.lw.dao.model.app.RedeemedReward;
import id.co.icg.lw.dao.model.app.User;

public interface RedeemManager {

    boolean approve(RedeemedReward redeem, int status, User user);
}
