/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.ie.filter;

import id.co.icg.ie.dao.model.app.AppAdminMenu;
import id.co.icg.ie.dao.model.app.User;
import id.co.icg.ie.web.BaseActionBean;
import id.co.icg.ie.web.credential.AccessDeniedActionBean;
import id.co.icg.ie.web.user.ChangePinActionBean;
import id.co.icg.ie.web.credential.LoginActionBean;
import id.co.icg.ie.web.credential.WelcomeActionBean;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.controller.StripesFilter;

/**
 *
 * @author Fauzi Marjalih
 */
@Intercepts({LifecycleStage.HandlerResolution})
public class FilterActionBean implements Interceptor {
    
    private HttpSession  session;
    
    @Override
    public Resolution intercept(ExecutionContext ctx) throws Exception {
        int isAccess   = 0;
        BaseActionBean actionBean = (BaseActionBean) ctx.getActionBean();
        if(actionBean.getClass().isAnnotationPresent(DoesNotRequireLogin.class)) isAccess = 1;
        
        User user = null;
        String url  = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(actionBean.getClass());
        String ad   = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(AccessDeniedActionBean.class);
        String head = "";
        
        if(actionBean.getClass().isAnnotationPresent(DependOnPage.class)) {
            DependOnPage dependOnPage = actionBean.getClass().getAnnotation(DependOnPage.class);
            head = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(dependOnPage.value());
        }
        
        
        
        if(isAccess == 0 && !ad.equals(url)) {
            session   = ctx.getActionBeanContext().getRequest().getSession();
            user = (User) session.getAttribute("SESSION_USER");
            
            if(user != null) {
                isAccess = 2;
                String welcomepage = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(WelcomeActionBean.class);
                String changepin   = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(ChangePinActionBean.class);
                if(welcomepage.endsWith(url) || changepin.endsWith(url)) isAccess = 1;
                else {
                    List<AppAdminMenu> menus = (List<AppAdminMenu>) session.getAttribute("SESSION_LINK");
                    for (AppAdminMenu menu : menus) {
                        if(menu.getUrlPath() != null && (menu.getUrlPath().endsWith(url) || menu.getUrlPath().endsWith(head))) {
                            isAccess = 1;
                            break;
                        }
                    }
                }
            }

        } else if(isAccess == 0 && ad.equals(url)) {
            isAccess = 1;
        }
        
        String loginpage  = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(LoginActionBean.class);
        
        if(user != null && loginpage.endsWith(url)) isAccess = 3;
        
        if(!url.contains("chat.html")) System.out.println("URL:" + url+" | ACCESS:" + isAccess+" | LOGIN:" + loginpage);
        
        switch (isAccess) {
            case 1:
                return ctx.proceed();
            case 2:
                return new RedirectResolution(AccessDeniedActionBean.class);
            case 3:
                return new RedirectResolution(WelcomeActionBean.class);
            default:
                return new RedirectResolution(LoginActionBean.class);
        }
    }
}
