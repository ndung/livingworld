package id.co.icg.lw.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class ImageThumbnailDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            String link = (String) columnValue;
            String xxx = "<img onclick=\"window.open(\'" + link + "\',\'_blank\');\" src=\"http://49.128.182.148:8085/images/" + link + "\" alt=\"\" style=\"height:7em!important\">";
            return xxx;
        } else {
            return columnValue;
        }
    }

}
