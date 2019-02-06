package id.co.icg.lw.web.member;

import id.co.icg.lw.dao.model.app.Message;
import id.co.icg.lw.manager.MessageManager;
import id.co.icg.lw.util.FcmSender;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

import java.util.Date;

@UrlBinding("/member/addmessage.html")
public class AddMessageActionBean extends ActionBeanClass {

    protected MessageManager messageManager;
    
    @ValidateNestedProperties({
        @Validate(field = "title", required = true),
        @Validate(field = "message", required = true)
    })    
    private Message message;

    @SpringBean("messageManager")
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {

    }
    
    @DontValidate
    public Resolution back() {
        return new RedirectResolution(MessageManagementActionBean.class);
    }
    
    public Resolution save() {
        message.setSender(getUserSession());
        message.setStatus(1);
        message.setCreateAt(new Date());
        if(messageManager.saveMessage(message)) {
            String resp = new FcmSender().sendMessage(message.getTitle(), message.getMessage(), "global");
            getContext().getMessages().add(new LocalizableMessage(resp));
        }
        else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        }
        return new RedirectResolution(MessageManagementActionBean.class);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

