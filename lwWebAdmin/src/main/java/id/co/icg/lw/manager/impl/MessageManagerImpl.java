package id.co.icg.lw.manager.impl;

import id.co.icg.lw.dao.DaoHibernate;
import id.co.icg.lw.dao.model.app.Message;
import id.co.icg.lw.manager.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("messageManager")
public class MessageManagerImpl implements MessageManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public boolean saveMessage(Message message) {
        return daoHibernate.addOnlyObject(message)!=null;
    }

    @Override
    public boolean editMessage(Message message) {
        return daoHibernate.updateObject(message);
    }

    @Override
    public boolean deleteMessage(Message message) {
        return daoHibernate.deleteObject(message);
    }

    @Override
    public boolean activateMessage(int status, Message message) {
        message.setStatus(status);
        return daoHibernate.updateObject(message);
    }
}
