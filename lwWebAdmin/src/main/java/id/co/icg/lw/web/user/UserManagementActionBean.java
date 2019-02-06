package id.co.icg.lw.web.user;

import id.co.icg.lw.dao.model.app.User;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.manager.BaseHibernateManager;
import id.co.icg.lw.manager.UserAdminManager;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/user/usermanagement.html")
public class UserManagementActionBean extends ActionBeanClass {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    private User appAdminUser;
    private UserAdminManager     userAdminManager;
    private BaseHibernateManager baseHibernateManager;
    
    private String               id;
    private String               fullName;

    @SpringBean("userAdminManager")
    public void setUserAdminManager(UserAdminManager userAdminManager) {
        this.userAdminManager = userAdminManager;
    }

    @SpringBean("baseHibernateManager")
    public void setBaseHibernateManager(BaseHibernateManager baseHibernateManager) {
        this.baseHibernateManager = baseHibernateManager;
    }
    
    @Before
    public void init() {
    }
    
    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
    }
    
    public Resolution add() {
        return new ForwardResolution(AddUserActionBean.class);
    }
    
    public Resolution activate() {
        if(appAdminUser!=null){
            userAdminManager.activateUserAdmin(appAdminUser.getId());
            getContext().getMessages().add(new LocalizableMessage("user.management.activate.success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("user.management.activate.fail"));
        return new RedirectResolution(this.getClass());
    }
    
    public Resolution deactivate() {
        if(appAdminUser!=null){
            userAdminManager.deactivateUserAdmin(appAdminUser.getId());
            getContext().getMessages().add(new LocalizableMessage("user.management.deactivate.success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("user.management.deactivate.fail"));
        return new RedirectResolution(this.getClass());
    }
    
    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(User.class);
        parameter.setIsNotNull("appRole");
        parameter.setDescOrders("createAt");
        if (getId() != null) parameter.setEqualsOrLikes("id", getId());
        if (getFullName() != null) parameter.setEqualsOrLikes("fullName", getFullName());
        parameter.setMaxRows(10);

        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }

    public User getAppAdminUser() {
        return appAdminUser;
    }

    public void setAppAdminUser(User appAdminUser) {
        this.appAdminUser = appAdminUser;
    }
}

