package id.co.icg.lw.web.offer;

import id.co.icg.lw.dao.model.app.CurrentOffer;
import id.co.icg.lw.manager.OfferManager;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

import java.util.UUID;

@UrlBinding("/offer/addoffer.html")
public class AddOfferActionBean extends ActionBeanClass {

    protected FileBean fileBean1;

    public FileBean getFileBean1() {
        return fileBean1;
    }

    public void setFileBean1(FileBean fileBean1) { this.fileBean1 = fileBean1; }

    protected FileBean fileBean2;

    public FileBean getFileBean2() {
        return fileBean2;
    }

    public void setFileBean2(FileBean fileBean2) {
        this.fileBean2 = fileBean2;
    }

    protected FileBean fileBean3;

    public FileBean getFileBean3() {
        return fileBean3;
    }

    public void setFileBean3(FileBean fileBean3) { this.fileBean3 = fileBean3; }

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
        if (fileBean1!=null && !(fileBean1.getFileName().endsWith(".jpeg")||fileBean1.getFileName().endsWith(".jpg")
                ||fileBean1.getFileName().endsWith(".png"))) {
            errors.add("fileBean1", new LocalizableError("file.wrong.format"));
        }
        if (fileBean2!=null && !(fileBean2.getFileName().endsWith(".jpeg")||fileBean2.getFileName().endsWith(".jpg")
                ||fileBean2.getFileName().endsWith(".png"))) {
            errors.add("fileBean2", new LocalizableError("file.wrong.format"));
        }
        if (fileBean3!=null && !(fileBean3.getFileName().endsWith(".jpeg")||fileBean3.getFileName().endsWith(".jpg")
                ||fileBean3.getFileName().endsWith(".png"))) {
            errors.add("fileBean3", new LocalizableError("file.wrong.format"));
        }
    }
    
    @DontValidate
    public Resolution back() {
        return new RedirectResolution(OfferManagementActionBean.class);
    }
    
    public Resolution save() {
        Long id = offerManager.saveCurrentOffer(currentOffer);
        if(id!=null) {
            CurrentOffer offer = offerManager.getCurrentOffer(id);
            if (fileBean1!=null) {
                offerManager.saveCurrentOfferImage(fileBean1, offer);
            }
            if (fileBean2!=null) {
                offerManager.saveCurrentOfferImage(fileBean2, offer);
            }
            if (fileBean3!=null) {
                offerManager.saveCurrentOfferImage(fileBean3, offer);
            }
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

