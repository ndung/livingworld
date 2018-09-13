package id.co.icg.ie.displaytag;

import id.co.icg.ie.util.DateTimeUtil;
import java.util.Date;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class DateFormatDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            try {
            	Date str = (Date) columnValue;
                return DateTimeUtil.convertDateToStringCustomized(str, "yyyy-MM-dd");
            } catch (Exception e) {
                return columnValue;
            }
        } else {
            return columnValue;
        }
    }
    
}
