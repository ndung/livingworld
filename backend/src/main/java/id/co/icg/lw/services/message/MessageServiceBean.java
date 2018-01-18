package id.co.icg.lw.services.message;

import id.co.icg.lw.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceBean implements MessageService {
    @Override
    public List<MessageRequest> getMessages(int page) {
        return null;
    }

    @Override
    public List<MessageRequest> getMessagesByUser(int page, String userId) {
        return null;
    }

    @Override
    public MessageRequest createMessage(MessageRequest messageRequest) {
        return null;
    }
}
