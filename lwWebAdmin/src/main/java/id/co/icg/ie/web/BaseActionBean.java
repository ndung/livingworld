package id.co.icg.ie.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public abstract class BaseActionBean implements ActionBean {

    private BaseActionBeanContext ctx;
    
    @Override
    public BaseActionBeanContext getContext() {
        return this.ctx;
    }
	
    @Override
    public void setContext(ActionBeanContext ctx) {
        this.ctx = (BaseActionBeanContext) ctx;		
    }
    
    
}
