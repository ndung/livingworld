package id.co.icg.lw.services.message;

import id.co.icg.lw.domain.Message;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.repositories.MessageRepository;
import id.co.icg.lw.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceBean implements MessageService {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @Value("${pageSize}")
    private int pageSize;

    @Override
    public List<Message> getMessages(int page) {
        return messageRepository.findAll(new PageRequest(page - 1, pageSize)).getContent();
    }

    @Override
    public List<MessageResponse> getMessagesByUser(int page, String userId) {
        List<MessageResponse> messageResponses = messageRepository.findAllByReceiverOrderByCreateAtDesc(userService.findOne(userId), new PageRequest(page - 1, pageSize));
        return messageResponses;
    }

    @Override
    public List<MessageResponse> getGlobalMessages(int page) throws Exception {
        return messageRepository.findAllByReceiverOrderByCreateAtDesc(null, new PageRequest(page, pageSize));
    }

    @Override
    public boolean saveMessageFromUser(String userId, SendMessageRequest sendMessageRequest) throws Exception{
        User sender = userService.findOne(userId);
        Message message = new Message();
        message.setTitle("Contact Us");
        message.setMessage(sendMessageRequest.getComment());
        message.setSender(sender);
        messageRepository.save(message);
        return true;
    }

    @Override
    public MessageRequest createMessage(MessageRequest messageRequest) throws Exception{
        User receiver = null;

        if (messageRequest.getReceiverId() != null ) {
            receiver = userService.findOne(messageRequest.getReceiverId());
        }

        Message message = new Message();
        message.setTitle(messageRequest.getTitle());
        message.setMessage(messageRequest.getMessage());
        message.setSender(null);
        message.setReceiver(receiver);
        messageRepository.save(message);
        return messageRequest;
    }
}
