package id.co.icg.lw.services.message;

import id.co.icg.lw.domain.LwMessage;

import java.util.Date;

public class MessageRequest {
    private String title;
    private String message;
    private Date date;

    public MessageRequest(String title, String message, Date date) {
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public MessageRequest(LwMessage lwMessage) {

    }

    public MessageRequest() {

    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
