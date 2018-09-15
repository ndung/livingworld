package id.co.icg.lw.manager.impl;

import id.co.icg.lw.dao.DaoHibernate;
import id.co.icg.lw.dao.model.app.CurrentOffer;
import id.co.icg.lw.dao.model.app.CurrentOfferImage;
import id.co.icg.lw.manager.FileManager;
import id.co.icg.lw.manager.OfferManager;
import net.sourceforge.stripes.action.FileBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Service("offerManager")
public class OfferManagerImpl implements OfferManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Autowired
    private FileManager fileManager;

    @Override
    public Long saveCurrentOffer(CurrentOffer currentOffer) {
        currentOffer.setCreateAt(new Date());
        Serializable result = daoHibernate.addOnlyObject(currentOffer);
        if (result!=null){
            return (Long) result;
        }
        return null;
    }

    @Override
    public CurrentOffer getCurrentOffer(Long id) {
        return (CurrentOffer) daoHibernate.getObject(CurrentOffer.class, id);
    }

    @Override
    public boolean editCurrentOffer(CurrentOffer currentOffer) {
        currentOffer.setUpdateAt(new Date());
        return daoHibernate.updateObject(currentOffer);
    }

    @Override
    public boolean deleteCurrentOffer(CurrentOffer currentOffer) {
        return daoHibernate.deleteObject(currentOffer);
    }

    @Override
    public boolean saveCurrentOfferImage(FileBean fileBean, CurrentOffer currentOffer) {
        try {
            String fileName = fileManager.saveFile(fileBean);
            CurrentOfferImage currentOfferImage = new CurrentOfferImage();
            currentOfferImage.setId(fileName);
            currentOfferImage.setCurrentOffer(currentOffer);
            return daoHibernate.addOnlyObject(currentOfferImage)!=null;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCurrentOfferImage(CurrentOfferImage currentOfferImage) {
        return daoHibernate.deleteObject(currentOfferImage);
    }

    @Override
    public boolean activateOffer(String status, CurrentOffer offer) {
        offer.setUpdateAt(new Date());
        offer.setActive(status);
        return daoHibernate.updateObject(offer);
    }
}
