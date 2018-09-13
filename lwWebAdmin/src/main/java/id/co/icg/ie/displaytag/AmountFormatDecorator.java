package id.co.icg.ie.displaytag;

import id.co.icg.ie.util.StringUtil;
import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class AmountFormatDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            try {
                double doub = 0.0;
                if(columnValue instanceof String)  doub = Double.parseDouble((String) columnValue); 
                if(columnValue instanceof Double)  doub = (Double) columnValue; 
                if(columnValue instanceof Integer) doub = Double.parseDouble(String.valueOf((Integer) columnValue));
                return StringUtil.numberFormat(doub);
            } catch (NumberFormatException e) {
                return columnValue;
            }
        } else {
            return columnValue;
        }
    }
    
}
