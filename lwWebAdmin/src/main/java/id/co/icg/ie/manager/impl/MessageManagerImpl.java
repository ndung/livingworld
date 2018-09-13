package id.co.icg.ie.manager.impl;

import id.co.icg.ie.dao.DaoHibernate;
import id.co.icg.ie.dao.model.app.Message;
import id.co.icg.ie.manager.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageManager")
public class MessageManagerImpl implements MessageManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public boolean saveMessage(Message message) {
        return daoHibernate.addOnlyObject(message);
    }

    @Override
    public boolean editMessage(Message message) {
        return daoHibernate.updateObject(message);
    }

    @Override
    public boolean deleteMessage(Message message) {
        return daoHibernate.deleteObject(message);
    }
}
