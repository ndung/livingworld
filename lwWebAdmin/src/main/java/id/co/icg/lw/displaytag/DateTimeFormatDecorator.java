package id.co.icg.lw.displaytag;

import id.co.icg.lw.util.DateTimeUtil;
import java.util.Date;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class DateTimeFormatDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            try {
            	Date str = (Date) columnValue;
                return DateTimeUtil.convertDateToStringCustomized(str, "yyyy-MM-dd HH:mm:ss");
            } catch (Exception e) {
                return columnValue;
            }
        } else {
            return columnValue;
        }
    }
    
}
