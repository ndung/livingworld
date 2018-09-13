package id.co.icg.ie.web.credential;


import id.co.icg.ie.web.ActionBeanClass;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/auth/welcome.html")
public class WelcomeActionBean extends ActionBeanClass {
    
    @DefaultHandler
    @DontValidate
    public Resolution view() {
        return new ForwardResolution(getView("credential", this.getClass()));
    }
	
}
