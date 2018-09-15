/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.lw.filter;

import id.co.icg.lw.util.StringUtil;
import id.co.icg.lw.web.credential.AccessDeniedActionBean;
import id.co.icg.lw.web.credential.LoginActionBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.ActionBeanNotFoundException;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;

/**
 *
 * @author Hatta Palino
 */
public class MyExceptionHandler extends DefaultExceptionHandler{

    private static final String VIEW = "/TEMPLATE-WEB/authentification/exception.jsp";

    public Resolution handle(ActionBeanNotFoundException e, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("ERROR : " + e);
        if (request.getSession().getAttribute(ConstantsUtil.SESSION_USER) != null) {
            return new RedirectResolution(AccessDeniedActionBean.class);
        } else {
            return new RedirectResolution(LoginActionBean.class);
        }
    }       
    
}
