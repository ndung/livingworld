/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.ie.manager;

import id.co.icg.ie.dao.model.app.User;
import java.util.List;

/**
 *
 * @author Fauzi Marjalih
 */
public interface UserAdminManager {
    User getUserAdmin(String username);
    User getUserAdmin(String username, String password);
    List<User>   getUserAdmins();
    boolean              activateUserAdmin(String username);
    boolean              deactivateUserAdmin(String username);
    boolean              blockUserAdmin(String username);
    boolean              saveUserAdmin(User userAdmin);
    boolean              editUserAdmin(User userAdmin);
    boolean              deleteUserAdmin(String username);
    boolean              getChangePassword(String username, String newPassword);
}
