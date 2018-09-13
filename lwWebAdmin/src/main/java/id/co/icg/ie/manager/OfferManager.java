package id.co.icg.ie.manager;

import id.co.icg.ie.dao.model.app.CurrentOffer;
import id.co.icg.ie.dao.model.app.CurrentOfferImage;
import net.sourceforge.stripes.action.FileBean;

public interface OfferManager {
    boolean saveCurrentOffer(CurrentOffer currentOffer);
    boolean editCurrentOffer(CurrentOffer currentOffer);
    boolean deleteCurrentOffer(CurrentOffer currentOffer);
    boolean saveCurrentOfferImage(FileBean fileBean, CurrentOffer currentOffer);
    boolean deleteCurrentOfferImage(CurrentOfferImage currentOfferImage);
}
