/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.ie.filter;

import id.co.icg.ie.util.StaticField;
import net.sourceforge.stripes.config.RuntimeConfiguration;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Hatta Palino
 * 
 */
public class StartupActionBean extends RuntimeConfiguration {

    @Override
    public void init() {
        StaticField.ctx          = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        StaticField.pathTemplate = getServletContext().getRealPath("/WEB-REPORTS/")+"/";
        StaticField.rootPath     = getServletContext().getContextPath();
        System.out.println("CONFIGURASI OK");
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
