package id.co.icg.lw.web.offer;

import id.co.icg.lw.dao.model.app.CurrentOffer;
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

@UrlBinding("/offer/offermanagement.html")
public class OfferManagementActionBean extends ActionBeanClass {

    private CurrentOffer currentOffer;

    public CurrentOffer getCurrentOffer() {
        return currentOffer;
    }

    public void setCurrentOffer(CurrentOffer currentOffer) {
        this.currentOffer = currentOffer;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private BaseHibernateManager baseHibernateManager;

    @SpringBean("baseHibernateManager")
    public void setBaseHibernateManager(BaseHibernateManager baseHibernateManager) {
        this.baseHibernateManager = baseHibernateManager;
    }

    private OfferManager offerManager;

    @SpringBean("offerManager")
    public void setOfferManager(OfferManager offerManager) {
        this.offerManager = offerManager;
    }

    @Before
    public void init() {
    }
    
    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
    }
    
    public Resolution add() {
        return new ForwardResolution(AddOfferActionBean.class);
    }

    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(CurrentOffer.class);
        parameter.setMaxRows(10);
        parameter.setDescOrders("createAt");
        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }

    @DontValidate
    public Resolution activate() {
        if(currentOffer!=null){
            boolean bool = offerManager.activateOffer("Y", currentOffer);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }

    @DontValidate
    public Resolution deactivate() {
        if(currentOffer!=null){
            boolean bool = offerManager.activateOffer("N", currentOffer);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }
}

