package id.co.icg.lw.web.offer;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/offer/editoffer.html")
public class EditOfferActionBean extends AddOfferActionBean {

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
        if (getCurrentOffer().getStartDate().after(getCurrentOffer().getEndDate())){
            errors.add("startDate", new LocalizableError("start.date.before.end.date"));
        }
    }

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

