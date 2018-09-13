package id.co.icg.ie.web.offer;


import id.co.icg.ie.dao.model.app.CurrentOffer;
import id.co.icg.ie.dao.model.app.User;
import id.co.icg.ie.dao.util.ParameterDao;
import id.co.icg.ie.manager.BaseHibernateManager;
import id.co.icg.ie.manager.UserAdminManager;
import id.co.icg.ie.web.ActionBeanClass;
import id.co.icg.ie.web.user.AddUserActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/offer/offermanagement.html")
public class OfferManagementActionBean extends ActionBeanClass {

    private String               title;

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

        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }


}

