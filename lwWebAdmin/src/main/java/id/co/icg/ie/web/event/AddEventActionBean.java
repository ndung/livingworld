package id.co.icg.ie.web.event;


import id.co.icg.ie.dao.model.app.CurrentOffer;
import id.co.icg.ie.dao.model.app.Event;
import id.co.icg.ie.manager.EventManager;
import id.co.icg.ie.manager.OfferManager;
import id.co.icg.ie.web.ActionBeanClass;
import id.co.icg.ie.web.offer.OfferManagementActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

@UrlBinding("/event/addevent.html")
public class AddEventActionBean extends ActionBeanClass {

    protected EventManager eventManager;
    
    @ValidateNestedProperties({
        @Validate(field = "name", required = true),
        @Validate(field = "description", required = true),
        @Validate(field = "startDate", required = true, converter = DateTypeConverter.class),
        @Validate(field = "endDate", required = true, converter = DateTypeConverter.class)
    })    
    private Event event;

    @SpringBean("eventManager")
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {

    }
    
    @DontValidate
    public Resolution back() {
        return new RedirectResolution(EventManagementActionBean.class);
    }
    
    public Resolution add() {
        if(eventManager.saveEvent(event)) {
            getContext().getMessages().add(new LocalizableMessage("success"));
        }
        else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        }
        return new RedirectResolution(EventManagementActionBean.class);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

