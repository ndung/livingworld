package id.co.icg.lw.displaytag;

import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class StatusYNFormatDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            try {
                String doub = (String) columnValue; 
                
                if(!doub.equals("") && doub.equals("Y")) {
                    return "Active";
                } else if(!doub.equals("") && doub.equals("N")) {
                    return "Deactive";
                } else {
                    return doub;
                }
            } catch (NumberFormatException e) {
                return columnValue;
            }
        } else {
            return columnValue;
        }
    }
    
}
