package id.co.icg.ie.web.offer;


import id.co.icg.ie.dao.model.app.AppAdminRole;
import id.co.icg.ie.dao.model.app.CurrentOffer;
import id.co.icg.ie.dao.model.app.User;
import id.co.icg.ie.manager.OfferManager;
import id.co.icg.ie.manager.RoleManager;
import id.co.icg.ie.manager.UserAdminManager;
import id.co.icg.ie.util.EncryptUtil;
import id.co.icg.ie.util.SelectValue;
import id.co.icg.ie.web.ActionBeanClass;
import id.co.icg.ie.web.user.UserManagementActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;


@UrlBinding("/offer/addoffer.html")
public class AddOfferActionBean extends ActionBeanClass {

    protected OfferManager offerManager;
    
    @ValidateNestedProperties({
        @Validate(field = "title", required = true),
        @Validate(field = "shortDescription", required = true),
        @Validate(field = "longDescription", required = true),
        @Validate(field = "startDate", required = true, converter = DateTypeConverter.class),
        @Validate(field = "endDate", required = true, converter = DateTypeConverter.class)
    })    
    private CurrentOffer currentOffer;

    @SpringBean("offerManager")
    public void setOfferManager(OfferManager offerManager) {
        this.offerManager = offerManager;
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {

    }
    
    @DontValidate
    public Resolution back() {
        return new RedirectResolution(OfferManagementActionBean.class);
    }
    
    public Resolution add() {
        if(offerManager.saveCurrentOffer(currentOffer)) {
            getContext().getMessages().add(new LocalizableMessage("success"));
        }
        else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        }
        return new RedirectResolution(OfferManagementActionBean.class);
    }

    public CurrentOffer getCurrentOffer() {
        return currentOffer;
    }

    public void setCurrentOffer(CurrentOffer currentOffer) {
        this.currentOffer = currentOffer;
    }
}

