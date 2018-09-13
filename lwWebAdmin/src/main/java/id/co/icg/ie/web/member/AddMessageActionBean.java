package id.co.icg.ie.web.member;


import id.co.icg.ie.dao.model.app.CurrentOffer;
import id.co.icg.ie.dao.model.app.Message;
import id.co.icg.ie.manager.MessageManager;
import id.co.icg.ie.manager.OfferManager;
import id.co.icg.ie.web.ActionBeanClass;
import id.co.icg.ie.web.offer.OfferManagementActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

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
    
    public Resolution add() {
        if(messageManager.saveMessage(message)) {
            getContext().getMessages().add(new LocalizableMessage("success"));
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

