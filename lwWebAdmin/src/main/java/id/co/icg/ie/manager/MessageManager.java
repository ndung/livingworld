package id.co.icg.ie.manager;

import id.co.icg.ie.dao.model.app.Message;

public interface MessageManager {
    boolean saveMessage(Message message);
    boolean editMessage(Message message);
    boolean deleteMessage(Message message);
}
