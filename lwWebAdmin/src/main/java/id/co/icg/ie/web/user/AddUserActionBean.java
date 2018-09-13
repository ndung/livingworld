package id.co.icg.ie.web.user;


import id.co.icg.ie.dao.model.app.AppAdminRole;
import id.co.icg.ie.dao.model.app.User;
import id.co.icg.ie.manager.RoleManager;
import id.co.icg.ie.manager.UserAdminManager;
import id.co.icg.ie.util.EncryptUtil;
import id.co.icg.ie.util.SelectValue;
import id.co.icg.ie.web.ActionBeanClass;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/user/adduser.html")
public class AddUserActionBean extends ActionBeanClass {

    protected UserAdminManager     userAdminManager;
    private   RoleManager          roleManager;
    
    @ValidateNestedProperties({
        @Validate(field = "id" , required = true),
        @Validate(field = "fullName" , required = true),
        @Validate(field = "password"  , required = true),
        @Validate(field = "email"    , required = true, converter = EmailTypeConverter.class),
        @Validate(field = "phone", required = true, minlength = 9, maxlength = 13),
        @Validate(field = "appRole.id", required = true)
    })    
    private User appUser;
    private String              rePassword;

    @SpringBean("userAdminManager")
    public void setUserAdminManager(UserAdminManager userAdminManager) {
        this.userAdminManager = userAdminManager;
    }

    @SpringBean("roleManager")
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }
    
    public List<SelectValue> getRoles() {
        List<SelectValue> pcs = new ArrayList();
        for (AppAdminRole appRole : roleManager.getRoles()) {
            pcs.add(new SelectValue(appRole.getId(), appRole.getName()));
        }
        return pcs;
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
        User au = userAdminManager.getUserAdmin(getAppUser().getId());
        if(au!=null) {
            errors.add("username" , new LocalizableError("user.management.add.exist", appUser.getId()));
        }
        if(getAppUser().getPhone()!=null && !appUser.getPhone().matches("[0-9]+")) {
            errors.add("phone" , new LocalizableError("user.myaccount.error.phone"));
        }
        if(rePassword==null || !appUser.getPassword().equals(rePassword)) {
            errors.add("password" , new LocalizableError("user.changepassword.error.confirm"));
        }
        if(!EncryptUtil.validatePassword(appUser.getPassword(), true, true, true, 8, 20)){
            errors.add("password", new LocalizableError("user.changepassword.error.format"));
        }
    }
    
    @DontValidate
    public Resolution back() {
        return new RedirectResolution(UserManagementActionBean.class);
    }
    
    public Resolution save() {
        appUser.setPassword(EncryptUtil.MD5(appUser.getPassword()));
        if(userAdminManager.saveUserAdmin(appUser)) {
            getContext().getMessages().add(new LocalizableMessage("user.management.add.success",appUser.getId()));
        }
        else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("user.management.add.fail", appUser.getId()));
        }
        return new RedirectResolution(UserManagementActionBean.class);
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

}

