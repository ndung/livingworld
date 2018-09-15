package id.co.icg.lw.manager;

import id.co.icg.lw.dao.model.app.Message;

public interface MessageManager {
    boolean saveMessage(Message message);
    boolean editMessage(Message message);
    boolean deleteMessage(Message message);
    boolean activateMessage(int status, Message message);
}
