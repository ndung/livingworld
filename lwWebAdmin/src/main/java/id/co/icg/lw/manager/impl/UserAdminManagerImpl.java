/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.lw.manager.impl;

import id.co.icg.lw.dao.DaoHibernate;
import id.co.icg.lw.manager.UserAdminManager;
import id.co.icg.lw.dao.model.app.User;
import id.co.icg.lw.dao.util.CurrentPage;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.util.EncryptUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fauzi Marjalih
 */
@Service("userAdminManager")
public class UserAdminManagerImpl implements UserAdminManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public User getUserAdmin(String username) {
        return (User) daoHibernate.getObject(User.class, username);
    }

    @Override
    public User getUserAdmin(String username, String password) {
        ParameterDao parameterDao = new ParameterDao(User.class);
        parameterDao.setEqualsOrLikes("id", username);
        parameterDao.setEqualsOrLikes("password", EncryptUtil.MD5(password));
        
        CurrentPage currentPage = daoHibernate.getList(parameterDao);
        List<User>  users       = currentPage.getPageItems();
        if(users != null && users.size() > 0) return users.get(0);
        else return null;
    }

    @Override
    public List<User> getUserAdmins() {
        return daoHibernate.getList(User.class);
    }

    @Override
    public boolean saveUserAdmin(User appUser) {
        return daoHibernate.addOnlyObject(appUser)!=null;
    }

    @Override
    public boolean editUserAdmin(User appUser) {
        return daoHibernate.updateObject(appUser);
    }

    @Override
    public boolean deleteUserAdmin(String username) {
        User appUser = getUserAdmin(username);
        if(appUser != null) return daoHibernate.deleteObject(appUser);
        else                  return false;
    }

    private boolean enableDisableUserAdmin(String username, int status) {
        User appUser = getUserAdmin(username);
        if(appUser != null) {
            appUser.setStatus(status);
            return daoHibernate.updateObject(appUser);
        }
        else return false;
    }

    @Override
    public boolean activateUserAdmin(String username) {
        return enableDisableUserAdmin(username, 1);
    }

    @Override
    public boolean deactivateUserAdmin(String username) {
        return enableDisableUserAdmin(username, 0);
    }

    @Override
    public boolean blockUserAdmin(String username) {
        return enableDisableUserAdmin(username, 0);
    }

    @Override
    public boolean getChangePassword(String username, String newPassword) {
        boolean status = false;
        User user   = getUserAdmin(username);
        if(user != null) {
            user.setPassword(EncryptUtil.MD5(newPassword));
            status = daoHibernate.updateObject(user);
        }
        return status;
    }

}
