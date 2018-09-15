package id.co.icg.lw.manager;

import id.co.icg.lw.dao.model.app.Reward;
import net.sourceforge.stripes.action.FileBean;

public interface RewardManager {
    boolean saveReward(Reward reward, FileBean fileBean);
    boolean editReward(Reward reward, FileBean fileBean);
    boolean deleteReward(Reward reward);
    boolean activateReward(String status, Reward reward);
}
