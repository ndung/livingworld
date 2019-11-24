package id.co.icg.lw.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class LinkDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            String link = (String) columnValue;
            return "<a onclick=\"window.open(\'" + link + "\',\'_blank\');\">Click Me</a>";
        } else {
            return columnValue;
        }
    }

}
