package id.co.icg.lw.web.offer;

import net.sourceforge.stripes.action.*;

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

