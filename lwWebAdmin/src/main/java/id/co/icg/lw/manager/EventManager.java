package id.co.icg.lw.manager;

import id.co.icg.lw.dao.model.app.Event;
import net.sourceforge.stripes.action.FileBean;

import java.util.List;

public interface EventManager {
    List<Event> getEvents();
    boolean saveEvent(Event event, FileBean fileBean);
    boolean editEvent(Event event, FileBean fileBean);
    boolean deleteEvent(Event event);
    boolean activateEvent(String status, Event event);
}
