/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.ie.manager.impl;

import id.co.icg.ie.dao.DaoHibernate;
import id.co.icg.ie.dao.model.app.AppAdminMenu;
import id.co.icg.ie.manager.RoleManager;
import id.co.icg.ie.dao.model.app.AppAdminRole;
import id.co.icg.ie.dao.model.app.AppAdminRoleMenu;
import id.co.icg.ie.dao.util.CurrentPage;
import id.co.icg.ie.dao.util.ParameterDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fauzi Marjalih
 */
@Service("roleManager")
public class RoleManagerImpl implements RoleManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public List<AppAdminRole> getRoles() {
        return daoHibernate.getList(AppAdminRole.class);
    }

    @Override
    public AppAdminRole getRole(int i) {
        return (AppAdminRole) daoHibernate.getObject(AppAdminRole.class, i);
    }

    @Override
    public boolean saveRole(AppAdminRole role) {
        return daoHibernate.addObject(role);
    }

    @Override
    public boolean updateRole(AppAdminRole role) {
        return daoHibernate.updateObject(role);
    }

    @Override
    public boolean deleteRole(int id) {
        AppAdminRole role = getRole(id);
        if(role != null) return daoHibernate.deleteObject(role);
        else return false;
    }    

    private List<AppAdminMenu> getMenuParents(String parent, Map<String, AppAdminMenu> others) {
        List<AppAdminMenu> menus = new ArrayList<>();
        if(others.get(parent) == null) {
            AppAdminMenu       menu  = getMenuParent(parent);
             while (menu != null) {
                menus.add(menu);
                if(menu.getParent() != null && others.get(menu.getParent()) == null) menu = getMenuParent(menu.getParent());
                else menu = null;
            }
        } 
        return menus;        
    }
    
    private AppAdminMenu getMenuParent(String parent) {
        ParameterDao parameterDao = new ParameterDao(AppAdminMenu.class);
        parameterDao.setEqualsOrLikes("id", parent);
        
        CurrentPage currentPage = daoHibernate.getList(parameterDao);
        List<AppAdminMenu> ms = currentPage.getPageItems();
        if(ms != null && ms.size() > 0) return ms.get(0);
        else                            return null; 
    }
    
    @Override
    public List<AppAdminMenu> getMenus(Long id) {
        Map<String, AppAdminMenu> maps = new TreeMap<>();
        ParameterDao parameterDao = new ParameterDao(AppAdminRoleMenu.class);
        parameterDao.setEqualsOrLikes("id.roleId", id);
        CurrentPage currentPage = daoHibernate.getList(parameterDao);
        List<AppAdminRoleMenu> rMenus = currentPage.getPageItems();
        
        List<String> ids = new ArrayList<>();
        for (AppAdminRoleMenu roleMenu : rMenus) {
            ids.add(roleMenu.getId().getMenuId());
        }
        
        parameterDao = new ParameterDao(AppAdminMenu.class);
        parameterDao.setIn("id", ids);
        parameterDao.setAscOrders("id");
        
        currentPage = daoHibernate.getList(parameterDao);
        List<AppAdminMenu> menus = currentPage.getPageItems();
        
        for (AppAdminMenu menu : menus) {
            maps.put(menu.getId(), menu);
            List<AppAdminMenu> parents = getMenuParents(menu.getParent(), maps);
            for (AppAdminMenu parent : parents) {
                maps.put(parent.getId(), parent);
            }
        }
        
        List<AppAdminMenu> menux = new ArrayList<>();
        menux.addAll(maps.values());
        return menux;
    }
}
