package id.co.icg.lw.displaytag;

import javax.servlet.jsp.PageContext;

import id.co.icg.lw.util.SpringPropertiesUtil;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class ImageThumbnailDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        String url = SpringPropertiesUtil.getProperty("app.file.get.url");
        if (columnValue != null) {
            String link = (String) columnValue;
            return "<img onclick=\"window.open(\'" + url+link + "\',\'_blank\');\" src=\""+url+link+"\" alt=\"\" style=\"height:7em!important\">";
        } else {
            return columnValue;
        }
    }

}
