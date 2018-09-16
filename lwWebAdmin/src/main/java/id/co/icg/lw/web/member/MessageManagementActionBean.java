package id.co.icg.lw.web.member;


import id.co.icg.lw.dao.model.app.Member;
import id.co.icg.lw.dao.model.app.Message;
import id.co.icg.lw.dao.model.app.User;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.manager.BaseHibernateManager;
import id.co.icg.lw.manager.MessageManager;
import id.co.icg.lw.manager.UserAdminManager;
import id.co.icg.lw.web.ActionBeanClass;
import id.co.icg.lw.web.user.AddUserActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/member/messages.html")
public class MessageManagementActionBean extends ActionBeanClass {

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private MessageManager messageManager;
    private BaseHibernateManager baseHibernateManager;
    
    private String               title;
    private String               text;

    @SpringBean("messageManager")
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @SpringBean("baseHibernateManager")
    public void setBaseHibernateManager(BaseHibernateManager baseHibernateManager) {
        this.baseHibernateManager = baseHibernateManager;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Before
    public void init() {
    }
    
    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
    }
    
    public Resolution add() {
        return new ForwardResolution(AddMessageActionBean.class);
    }

    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(Message.class);
        if (getTitle() != null) parameter.setEqualsOrLikes("title", getTitle());
        if (getText() != null) parameter.setEqualsOrLikes("message", getText());
        parameter.setDescOrders("createAt");
        parameter.setMaxRows(10);

        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }

    @DontValidate
    public Resolution activate() {
        if(message!=null){
            messageManager.activateMessage(1, message);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }


    @DontValidate
    public Resolution deactivate() {
        if(message!=null){
            messageManager.activateMessage(0, message);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }
}

