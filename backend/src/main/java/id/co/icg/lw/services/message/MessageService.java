package id.co.icg.lw.services.message;

import id.co.icg.lw.domain.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessages(int page) throws Exception;
    List<MessageResponse> getMessagesByUser(int page, String userId) throws Exception;
    List<MessageResponse> getGlobalMessages(int page) throws Exception;
    boolean saveMessageFromUser(String userId, SendMessageRequest sendMessageRequest) throws Exception;
    MessageRequest createMessage(MessageRequest messageRequest) throws Exception;


}
