package id.co.icg.ie.web.offer;


import id.co.icg.ie.dao.model.app.CurrentOffer;
import id.co.icg.ie.manager.OfferManager;
import id.co.icg.ie.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

@UrlBinding("/offer/editoffer.html")
public class EditOfferActionBean extends AddOfferActionBean {

    public Resolution update() {
        offerManager.editCurrentOffer(getCurrentOffer());
        return back();
    }

    @DontValidate
    public Resolution delete() {
        offerManager.deleteCurrentOffer(getCurrentOffer());
        return back();
    }
}

