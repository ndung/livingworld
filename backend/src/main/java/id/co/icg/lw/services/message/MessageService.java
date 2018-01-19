package id.co.icg.lw.services.message;

import id.co.icg.lw.domain.LwMessage;
import id.co.icg.lw.domain.user.User;

import java.util.List;

public interface MessageService {
    List<MessageRequest> getMessages(int page);
    List<MessageRequest> getMessagesByUser(int page, String userId);
    boolean saveMessageFromUser(String userId, SendMessageRequest sendMessageRequest);
    MessageRequest createMessage(MessageRequest messageRequest);

}
