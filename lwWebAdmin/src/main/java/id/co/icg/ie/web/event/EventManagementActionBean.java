package id.co.icg.ie.web.event;


import id.co.icg.ie.dao.model.app.CurrentOffer;
import id.co.icg.ie.dao.model.app.Event;
import id.co.icg.ie.dao.util.ParameterDao;
import id.co.icg.ie.manager.BaseHibernateManager;
import id.co.icg.ie.web.ActionBeanClass;
import id.co.icg.ie.web.offer.AddOfferActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/event/eventmanagement.html")
public class EventManagementActionBean extends ActionBeanClass {

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
        return new ForwardResolution(AddEventActionBean.class);
    }

    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(Event.class);
        parameter.setMaxRows(10);

        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }


}

