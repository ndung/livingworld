package id.co.icg.lw.web.user;

import id.co.icg.lw.dao.model.app.AppAdminRole;
import id.co.icg.lw.dao.model.app.Merchant;
import id.co.icg.lw.dao.model.app.User;
import id.co.icg.lw.manager.MerchantManager;
import id.co.icg.lw.manager.RoleManager;
import id.co.icg.lw.manager.UserAdminManager;
import id.co.icg.lw.util.EncryptUtil;
import id.co.icg.lw.util.SelectValue;
import id.co.icg.lw.web.ActionBeanClass;
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
    protected MerchantManager      merchantManager;
    
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

    @SpringBean("merchantManager")
    public void setMerchantManager(MerchantManager merchantManager) {
        this.merchantManager = merchantManager;
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
        if(appUser.getEmail()!=null && !isValidEmailAddress(appUser.getEmail())){
            errors.add("email" , new LocalizableError("user.myaccount.error.email"));
        }
        if(appUser.getPhone()!=null && !appUser.getPhone().matches("[0-9]+")) {
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
        if (appUser.getAppRole().getId()!=2){
            appUser.setMerchant(null);
        }
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

    public List<SelectValue> getMerchants() {
        List<SelectValue> pcs = new ArrayList();
        for (Merchant merchant : merchantManager.getMerchants()) {
            pcs.add(new SelectValue(merchant.getId(), merchant.getName()));
        }
        return pcs;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}

