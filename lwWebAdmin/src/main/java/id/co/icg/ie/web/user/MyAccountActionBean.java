package id.co.icg.ie.web.user;


import id.co.icg.ie.dao.model.app.User;
import id.co.icg.ie.manager.UserAdminManager;
import id.co.icg.ie.web.ActionBeanClass;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/user/myaccount.html")
public class MyAccountActionBean extends ActionBeanClass {
    
    @Validate(required=true) 
    private String fullName;
    
    @Validate(required=true) 
    private String userTelegram;
    
    @Validate(required=true, converter = EmailTypeConverter.class) 
    private String email;
    
    @Validate(required=true, minlength = 9, maxlength = 13) 
    private String phone;
    
    private User appUser;
    private UserAdminManager userAdminManager;

    @SpringBean("userAdminManager")
    public void setUserAdminManager(UserAdminManager userAdminManager) {
        this.userAdminManager = userAdminManager;
    }

    @Before
    public void init() {
        setAppUser(userAdminManager.getUserAdmin(getUserSession().getId()));
    }
    
    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
        User au = userAdminManager.getUserAdmin(appUser.getId());
        if(au==null) errors.add("myaccount" , new LocalizableError("user.myaccount.error"));
        if(phone!=null && !phone.matches("[0-9]+")) errors.add("myaccount" , new LocalizableError("user.myaccount.error.phone"));
    }
    
    public Resolution update() {
        if(!appUser.getFullName().equals(fullName)) appUser.setFullName(fullName);
        if(!appUser.getPhone().equals(phone)) appUser.setPhone(phone);
        if(!appUser.getEmail().equals(email)) appUser.setEmail(email);
        boolean resp = userAdminManager.editUserAdmin(appUser);
        if(resp) {
            getContext().getMessages().add(new LocalizableMessage("user.myaccount.success"));
            User au = userAdminManager.getUserAdmin(appUser.getId());
            au.setPassword(au.getId());
            setUserSession(au);
        } else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("user.myaccount.fail"));
        }
        return new RedirectResolution(this.getClass());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }
}

