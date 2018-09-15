package id.co.icg.lw.displaytag;

import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class ApprovalStatusDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            try {
                String doub = "";
                if(columnValue instanceof String)  doub = (String) columnValue; 
                else if(columnValue instanceof Integer) doub = String.valueOf(columnValue);
                
                if(!doub.equals("") && doub.equals("1")) {
                    return "Approved";
                } else if(!doub.equals("") && doub.equals("2")) {
                    return "Rejected";
                } else if(!doub.equals("") && doub.equals("0")) {
                    return "Pending";
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
