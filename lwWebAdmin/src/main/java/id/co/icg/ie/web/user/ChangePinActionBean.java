package id.co.icg.ie.web.user;


import id.co.icg.ie.dao.model.app.User;
import id.co.icg.ie.manager.UserAdminManager;
import id.co.icg.ie.util.EncryptUtil;
import id.co.icg.ie.web.ActionBeanClass;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/user/changepassword.html")
public class ChangePinActionBean extends ActionBeanClass {
    
    @Validate(required=true) 
    private String               password;
    
    @Validate(required=true) 
    private String               reNewPassword;
    
    @Validate(required=true) 
    private String               newPassword;
    
    private User user;
    private UserAdminManager     userAdminManager;

    @SpringBean("userAdminManager")
    public void setUserAdminManager(UserAdminManager userAdminManager) {
        this.userAdminManager = userAdminManager;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
        user = userAdminManager.getUserAdmin(getUserSession().getId());
        if(!user.getPassword().equals(EncryptUtil.MD5(password))) errors.add("oldpassword" , new LocalizableError("user.changepassword.error"));
        if(!newPassword.equals(reNewPassword))   errors.add("newpassword" , new LocalizableError("user.changepassword.error.confirm"));
        if(!EncryptUtil.validatePassword(newPassword, true, true, true, 8, 14)) errors.add("newpassword" , new LocalizableError("user.changepassword.error.format"));
    }
    
    public Resolution update() {
        if(userAdminManager.getChangePassword(getUserSession().getId(), newPassword)) getContext().getMessages().add(new LocalizableMessage("user.changepassword.success"));
        else getContext().getValidationErrors().addGlobalError(new LocalizableError("user.changepassword.fail"));
        return new RedirectResolution(this.getClass());
    }
}

