package id.co.icg.ie.web.member;


import id.co.icg.ie.dao.model.app.User;
import id.co.icg.ie.dao.model.app.Member;
import id.co.icg.ie.dao.util.ParameterDao;
import id.co.icg.ie.manager.BaseHibernateManager;
import id.co.icg.ie.manager.UserAdminManager;
import id.co.icg.ie.web.ActionBeanClass;
import id.co.icg.ie.web.user.AddUserActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/member/list.html")
public class MemberManagementActionBean extends ActionBeanClass {

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

    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(Member.class);
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

