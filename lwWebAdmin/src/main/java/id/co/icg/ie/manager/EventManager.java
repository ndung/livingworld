package id.co.icg.ie.manager;

import id.co.icg.ie.dao.model.app.Event;

public interface EventManager {
    boolean saveEvent(Event event);
    boolean editEvent(Event event);
    boolean deleteEvent(Event event);
}
