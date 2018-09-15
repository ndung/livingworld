package id.co.icg.lw.manager.impl;

import id.co.icg.lw.dao.DaoHibernate;
import id.co.icg.lw.dao.model.app.AppAdminRole;
import id.co.icg.lw.dao.model.app.Event;
import id.co.icg.lw.manager.EventManager;
import id.co.icg.lw.manager.FileManager;
import net.sourceforge.stripes.action.FileBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("eventManager")
public class EventManagerImpl implements EventManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Autowired
    FileManager fileManager;

    @Override
    public List<Event> getEvents() { return daoHibernate.getList(Event.class); }

    @Override
    public boolean saveEvent(Event event, FileBean fileBean) {
        String fileName = fileManager.saveFile(fileBean);
        event.setImage(fileName);
        return daoHibernate.addOnlyObject(event)!=null;
    }

    @Override
    public boolean editEvent(Event event, FileBean fileBean) {
        if (fileBean!=null) {
            String fileName = fileManager.saveFile(fileBean);
            event.setImage(fileName);
        }
        return daoHibernate.updateObject(event);
    }

    @Override
    public boolean deleteEvent(Event event) {
        return daoHibernate.deleteObject(event);
    }

    @Override
    public boolean activateEvent(String status, Event event) {
        event.setUpdateAt(new Date());
        event.setActive(status);
        return daoHibernate.updateObject(event);
    }
}
