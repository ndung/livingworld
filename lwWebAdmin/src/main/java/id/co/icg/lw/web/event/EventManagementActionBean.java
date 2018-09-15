package id.co.icg.lw.web.event;

import id.co.icg.lw.dao.model.app.Event;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.manager.BaseHibernateManager;
import id.co.icg.lw.manager.EventManager;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/event/eventmanagement.html")
public class EventManagementActionBean extends ActionBeanClass {

    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    private EventManager eventManager;
    private BaseHibernateManager baseHibernateManager;

    @SpringBean("baseHibernateManager")
    public void setBaseHibernateManager(BaseHibernateManager baseHibernateManager) {
        this.baseHibernateManager = baseHibernateManager;
    }

    @SpringBean("eventManager")
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
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

    @DontValidate
    public Resolution activate() {
        if(event!=null){
            eventManager.activateEvent("Y", event);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }


    @DontValidate
    public Resolution deactivate() {
        if(event!=null){
            eventManager.activateEvent("N", event);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }
}

