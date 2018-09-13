package id.co.icg.ie.manager.impl;

import id.co.icg.ie.dao.DaoHibernate;
import id.co.icg.ie.dao.model.app.Event;
import id.co.icg.ie.manager.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("eventManager")
public class EventManagerImpl implements EventManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public boolean saveEvent(Event event) {
        return daoHibernate.addOnlyObject(event);
    }

    @Override
    public boolean editEvent(Event event) {
        return daoHibernate.updateObject(event);
    }

    @Override
    public boolean deleteEvent(Event event) {
        return daoHibernate.deleteObject(event);
    }
}
