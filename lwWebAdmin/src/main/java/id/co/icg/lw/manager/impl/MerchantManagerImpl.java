package id.co.icg.lw.manager.impl;

import id.co.icg.lw.dao.DaoHibernate;
import id.co.icg.lw.dao.model.app.Merchant;
import id.co.icg.lw.manager.MerchantManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("merchantManager")
public class MerchantManagerImpl implements MerchantManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public List<Merchant> getMerchants() { return daoHibernate.getList(Merchant.class); }
}
