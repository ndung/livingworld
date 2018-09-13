package id.co.icg.ie.displaytag;

import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class StatusRedeemDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            try {
                String doub = "";
                if(columnValue instanceof String)  doub = (String) columnValue; 
                else if(columnValue instanceof Integer) doub = String.valueOf(columnValue);
                
                if(!doub.equals("") && doub.equals("1")) {
                    return "System Passed";
                } else if(!doub.equals("") && doub.equals("0")) {
                    return "Failed";
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
