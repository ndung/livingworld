package id.co.icg.ie.manager;

import id.co.icg.ie.dao.model.app.AppAdminMenu;
import id.co.icg.ie.dao.model.app.AppAdminRole;
import java.util.List;


public interface RoleManager{
    List<AppAdminMenu> getMenus(Long id);
    List<AppAdminRole> getRoles();
    AppAdminRole       getRole(int i);
    boolean       saveRole(AppAdminRole role);
    boolean       updateRole(AppAdminRole role);
    boolean       deleteRole(int id);
}
