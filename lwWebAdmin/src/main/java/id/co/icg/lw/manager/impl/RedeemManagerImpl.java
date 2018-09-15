package id.co.icg.lw.manager.impl;

import id.co.icg.lw.dao.DaoHibernate;
import id.co.icg.lw.dao.model.app.RedeemedReward;
import id.co.icg.lw.dao.model.app.User;
import id.co.icg.lw.manager.RedeemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("redeemManager")
public class RedeemManagerImpl implements RedeemManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public boolean approve(RedeemedReward redeem, int status, User user) {
        redeem.setApprovedStatus(status);
        redeem.setApprovedUser(user);
        redeem.setApprovedDate(new Date());
        return daoHibernate.updateObject(redeem);
    }
}
