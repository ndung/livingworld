package id.co.icg.ie.web.credential;


import id.co.icg.ie.dao.model.app.AppAdminMenu;
import id.co.icg.ie.dao.model.app.User;
import id.co.icg.ie.filter.ConstantsUtil;
import id.co.icg.ie.filter.DoesNotRequireLogin;
import id.co.icg.ie.manager.RoleManager;
import id.co.icg.ie.manager.UserAdminManager;
import id.co.icg.ie.util.EncryptUtil;
import id.co.icg.ie.web.ActionBeanClass;

import java.util.List;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import org.apache.log4j.Logger;

@DoesNotRequireLogin
@UrlBinding("/auth/login.html")
public class LoginActionBean extends ActionBeanClass {
    
    private final Logger logger = Logger.getLogger(this.getClass());

    @Validate(required = true)
    private String username;
    @Validate(required = true)
    private String password;
    private String phone;
    
    private UserAdminManager        userAdminManager;
    private RoleManager             roleManager;
    
    @SpringBean("userAdminManager")
    public void setUserAdminManager(UserAdminManager userAdminManager) {
        this.userAdminManager = userAdminManager;
    }

    @SpringBean("roleManager")
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public Resolution login() {
        User user = userAdminManager.getUserAdmin(username);
        if(user != null) {
            if(user.getPassword().equals(EncryptUtil.MD5(password))&&user.getStatus()==ConstantsUtil.LOGIN_STATUS_ACTIVE){

                user.setPassword(user.getId());
                setUserSession(user);
                List<AppAdminMenu> menus = roleManager.getMenus(user.getAppRole().getId());
                setMenuSession(menus);

                getContext().getMessages().add(new LocalizableMessage("credential.login.ok", user.getFullName()));
                return new RedirectResolution(WelcomeActionBean.class);
            } else if(user.getStatus()!=ConstantsUtil.LOGIN_STATUS_DEACTIVE){
                getContext().getValidationErrors().addGlobalError(new LocalizableError("credential.login.nok"));
                return view();
            } else {
                getContext().getValidationErrors().addGlobalError(new LocalizableError("credential.login.deactive"));
                return view();
            }
        } else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("credential.login.nok"));
            return view();
        }
    }
    
    @DontValidate
    public Resolution logout() {
        getContext().destroySession();
        return new RedirectResolution(LoginActionBean.class);
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return username;
    }
	
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    
}
