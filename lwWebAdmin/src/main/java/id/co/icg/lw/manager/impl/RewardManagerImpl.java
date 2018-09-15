package id.co.icg.lw.manager.impl;

import id.co.icg.lw.dao.DaoHibernate;
import id.co.icg.lw.dao.model.app.Reward;
import id.co.icg.lw.manager.FileManager;
import id.co.icg.lw.manager.RewardManager;
import net.sourceforge.stripes.action.FileBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("rewardManager")
public class RewardManagerImpl implements RewardManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Autowired
    private FileManager fileManager;

    @Override
    public boolean saveReward(Reward reward, FileBean fileBean) {
        String fileName = fileManager.saveFile(fileBean);
        reward.setImage(fileName);
        reward.setCreateAt(new Date());
        return daoHibernate.addOnlyObject(reward)!=null;
    }

    @Override
    public boolean editReward(Reward reward, FileBean fileBean) {
        if (fileBean!=null) {
            String fileName = fileManager.saveFile(fileBean);
            reward.setImage(fileName);
        }
        reward.setUpdateAt(new Date());
        return daoHibernate.updateObject(reward);
    }

    @Override
    public boolean deleteReward(Reward reward) {
        return daoHibernate.deleteObject(reward);
    }

    @Override
    public boolean activateReward(String status, Reward reward) {
        reward.setUpdateAt(new Date());
        reward.setActive(status);
        return daoHibernate.updateObject(reward);
    }
}
