package id.co.icg.ie.web.event;


import id.co.icg.ie.web.offer.AddOfferActionBean;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/event/editevent.html")
public class EditEventActionBean extends AddEventActionBean {

    public Resolution update() {
        eventManager.editEvent(getEvent());
        return back();
    }

    @DontValidate
    public Resolution delete() {
        eventManager.deleteEvent(getEvent());
        return back();
    }
}

