package id.co.icg.lw.web.credential;


import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/auth/accessdenied.html")
public class AccessDeniedActionBean extends ActionBeanClass {
    
    @DefaultHandler
    @DontValidate
    public Resolution view() {
        return new ForwardResolution(getView("credential", this.getClass()));
    }
	
}
