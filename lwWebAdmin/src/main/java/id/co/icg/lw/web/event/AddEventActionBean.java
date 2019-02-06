package id.co.icg.lw.web.event;

import id.co.icg.lw.dao.model.app.Event;
import id.co.icg.lw.manager.EventManager;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

@UrlBinding("/event/addevent.html")
public class AddEventActionBean extends ActionBeanClass {

    protected FileBean fileBean;

    public FileBean getFileBean() {
        return fileBean;
    }

    public void setFileBean(FileBean fileBean) { this.fileBean = fileBean; }

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
        if (event.getStartDate().after(event.getEndDate())){
            errors.add("startDate", new LocalizableError("start.date.before.end.date"));
        }
        if (fileBean == null) {
            errors.add("fileBean", new LocalizableError("file.not.found"));
        } else if (!(fileBean.getFileName().toLowerCase().endsWith(".jpeg")||
                fileBean.getFileName().toLowerCase().endsWith(".jpg")||
                fileBean.getFileName().toLowerCase().endsWith(".png"))) {
            errors.add("fileBean", new LocalizableError("file.wrong.format"));
        }
    }
    
    @DontValidate
    public Resolution back() {
        return new RedirectResolution(EventManagementActionBean.class);
    }
    
    public Resolution save() {
        if(eventManager.saveEvent(event, fileBean)) {
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

