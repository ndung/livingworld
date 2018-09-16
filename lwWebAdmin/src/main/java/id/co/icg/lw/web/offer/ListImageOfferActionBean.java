package id.co.icg.lw.web.offer;

import id.co.icg.lw.dao.model.app.CurrentOffer;
import id.co.icg.lw.dao.model.app.CurrentOfferImage;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.manager.BaseHibernateManager;
import id.co.icg.lw.manager.OfferManager;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/offer/listimageoffer.html")
public class ListImageOfferActionBean extends ActionBeanClass {

    protected FileBean fileBean;

    public FileBean getFileBean() {
        return fileBean;
    }

    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    private CurrentOffer currentOffer;

    public void setCurrentOffer(CurrentOffer currentOffer) {
        this.currentOffer = currentOffer;
    }

    private CurrentOfferImage currentOfferImage;

    public CurrentOfferImage getCurrentOfferImage() {
        return currentOfferImage;
    }

    public void setCurrentOfferImage(CurrentOfferImage currentOfferImage) {
        this.currentOfferImage = currentOfferImage;
    }

    private BaseHibernateManager baseHibernateManager;

    @SpringBean("baseHibernateManager")
    public void setBaseHibernateManager(BaseHibernateManager baseHibernateManager) {
        this.baseHibernateManager = baseHibernateManager;
    }

    @SpringBean("offerManager")
    private OfferManager offerManager;

    public void setOfferManager(OfferManager offerManager) {
        this.offerManager = offerManager;
    }

    @Before
    public void init() {
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
        if (fileBean == null) {
            errors.add("fileBean", new LocalizableError("file.not.found"));
        } else if (!(fileBean.getFileName().endsWith(".jpeg")||fileBean.getFileName().endsWith(".jpg")||fileBean.getFileName().endsWith(".png")||fileBean.getFileName().endsWith(".mp4"))) {
            errors.add("fileBean", new LocalizableError("file.wrong.format"));
        }
    }
    
    public Resolution add() {
        offerManager.saveCurrentOfferImage(fileBean, currentOffer);
        return new RedirectResolution(this.getClass()).addParameter("currentOffer", currentOffer);
    }

    @DontValidate
    public Resolution back() {
        return new RedirectResolution(OfferManagementActionBean.class);
    }

    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(CurrentOfferImage.class);
        if (currentOffer != null) parameter.setEqualsOrLikes("currentOffer", currentOffer);
        parameter.setMaxRows(10);

        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }

    @DontValidate
    public Resolution delete() {
        if(currentOfferImage!=null){
            offerManager.deleteCurrentOfferImage(currentOfferImage);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return back();
        //return new RedirectResolution(this.getClass()).addParameter("currentOffer", currentOffer);
    }

}

