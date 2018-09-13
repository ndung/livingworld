package id.co.icg.ie.manager.impl;

import id.co.icg.ie.dao.DaoHibernate;
import id.co.icg.ie.dao.model.app.CurrentOffer;
import id.co.icg.ie.dao.model.app.CurrentOfferImage;
import id.co.icg.ie.manager.FileManager;
import id.co.icg.ie.manager.OfferManager;
import net.sourceforge.stripes.action.FileBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service("offerManager")
public class OfferManagerImpl implements OfferManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public boolean saveCurrentOffer(CurrentOffer currentOffer) {
        return daoHibernate.addOnlyObject(currentOffer);
    }

    @Override
    public boolean editCurrentOffer(CurrentOffer currentOffer) {
        return daoHibernate.updateObject(currentOffer);
    }

    @Override
    public boolean deleteCurrentOffer(CurrentOffer currentOffer) {
        return daoHibernate.deleteObject(currentOffer);
    }

    @Override
    public boolean saveCurrentOfferImage(FileBean fileBean, CurrentOffer currentOffer) {
        try {
            CurrentOfferImage currentOfferImage = new CurrentOfferImage();
            fileBean.save(new File("C:\\Workplace\\IE\\tomcat7\\webapps\\image\\" + fileBean.getFileName()));
            currentOfferImage.setId(fileBean.getFileName());
            currentOfferImage.setCurrentOffer(currentOffer);
            return daoHibernate.addOnlyObject(currentOfferImage);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCurrentOfferImage(CurrentOfferImage currentOfferImage) {
        return daoHibernate.deleteObject(currentOfferImage);
    }
}
