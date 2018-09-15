package id.co.icg.lw.web.event;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/event/editevent.html")
public class EditEventActionBean extends AddEventActionBean {

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {

    }

    public Resolution update() {
        eventManager.editEvent(getEvent(), fileBean);
        return back();
    }

    @DontValidate
    public Resolution delete() {
        eventManager.deleteEvent(getEvent());
        return back();
    }
}

