package id.co.icg.lw.web.user;

import id.co.icg.lw.dao.model.app.User;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/user/edituser.html")
public class EditUserActionBean extends AddUserActionBean {

    @ValidateNestedProperties({
        @Validate(field = "id" , required = true),
        @Validate(field = "fullName" , required = true),
        @Validate(field = "email"    , required = true, converter = EmailTypeConverter.class),
        @Validate(field = "phone", required = true, minlength = 9, maxlength = 13),
        @Validate(field = "appRole.id", required = true)
    })    
    private User appUser;
    
    @DontValidate
    @DefaultHandler
    @Override
    public Resolution view() {
        if(appUser!=null){
            return new ForwardResolution(getView("user", this.getClass()));
        } else{
            getContext().getValidationErrors().addGlobalError(new LocalizableError("user.management.user.notfound", appUser.getId()));
            return new ForwardResolution(UserManagementActionBean.class);
        }
    }
    
    @ValidationMethod
    @Override
    public void otherCheck(ValidationErrors errors) {
        User au = userAdminManager.getUserAdmin(appUser.getId());
        if(au==null) {
            errors.add("username" , new LocalizableError("user.management.user.notfound", appUser.getId()));
        }
        if (appUser.getAppRole().getId()!=2){
            appUser.setMerchant(null);
        }
        if(appUser.getEmail()!=null && !isValidEmailAddress(appUser.getEmail())){
            errors.add("email" , new LocalizableError("user.myaccount.error.email"));
        }
        if(appUser.getPhone()!=null && !appUser.getPhone().matches("[0-9]+")) {
            errors.add("phone" , new LocalizableError("user.myaccount.error.phone"));
        }
    }
    
    public Resolution delete() {
        if(appUser!=null&&getUserSession().getAppRole().getName().equals("Administrator")){
            boolean resp = userAdminManager.deleteUserAdmin(appUser.getId());
            if(resp) {
                getContext().getMessages().add(new LocalizableMessage("user.management.delete.success", appUser.getId()));
            } else {
                getContext().getMessages().add(new LocalizableMessage("user.management.delete.fail", appUser.getId()));
            }
        } else{
            getContext().getValidationErrors().addGlobalError(new LocalizableError("user.management.delete.fail.privilledge", appUser.getId()));
        }
        return back();
    }
    
    public Resolution update() {
        if (appUser.getAppRole().getId()!=2){
            appUser.setMerchant(null);
        }
        boolean resp = userAdminManager.editUserAdmin(appUser);
        if(resp) {
            getContext().getMessages().add(new LocalizableMessage("user.management.update.success", appUser.getId()));
        } else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("user.management.update.fail", appUser.getId()));
        }
        return new RedirectResolution(this.getClass()).addParameter("appUser", appUser);
    }

    @Override
    public User getAppUser() {
        return appUser;
    }

    @Override
    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }

}

