package id.co.icg.lw.services.message;

import id.co.icg.lw.domain.Message;

import java.text.SimpleDateFormat;

public class MessageResponse {
    private Long id;
    private String title;
    private String message;
    private String date;

    public MessageResponse() {}

    public MessageResponse(Message message) {
        this.id = message.getMessageId();
        this.title = message.getTitle();
        this.message = message.getMessage();
        this.date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(message.getCreateAt());
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
