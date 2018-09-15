package id.co.icg.lw.manager;

import id.co.icg.lw.dao.model.app.CurrentOffer;
import id.co.icg.lw.dao.model.app.CurrentOfferImage;
import id.co.icg.lw.dao.model.app.Reward;
import net.sourceforge.stripes.action.FileBean;

import java.io.Serializable;

public interface OfferManager {
    Long saveCurrentOffer(CurrentOffer currentOffer);
    CurrentOffer getCurrentOffer(Long id);
    boolean editCurrentOffer(CurrentOffer currentOffer);
    boolean deleteCurrentOffer(CurrentOffer currentOffer);
    boolean saveCurrentOfferImage(FileBean fileBean, CurrentOffer currentOffer);
    boolean deleteCurrentOfferImage(CurrentOfferImage currentOfferImage);
    boolean activateOffer(String status, CurrentOffer offer);
}
