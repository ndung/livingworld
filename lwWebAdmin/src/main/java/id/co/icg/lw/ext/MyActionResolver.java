package id.co.icg.lw.ext;

import net.sourceforge.stripes.controller.NameBasedActionResolver;

public class MyActionResolver extends NameBasedActionResolver {
    
    @Override
    protected String getBindingSuffix() {
        return ".html";
    }
	
}