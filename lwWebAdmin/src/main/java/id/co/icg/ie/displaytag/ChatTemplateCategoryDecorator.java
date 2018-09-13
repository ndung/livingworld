package id.co.icg.ie.displaytag;

import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class ChatTemplateCategoryDecorator implements DisplaytagColumnDecorator {

    @Override
    public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
        if (columnValue != null) {
            try {
                String doub = (String) columnValue; 
                
                switch (doub) {
                    case "info":
                        return "Information";
                    case "general":
                        return "General";
                    case "transaction":
                        return "Trasaction";
                    case "campaign":
                        return "Campaign";
                    case "video":
                        return "Video";
                    default:
                        return "";
                }
            } catch (NumberFormatException e) {
                return columnValue;
            }
        } else {
            return columnValue;
        }
    }
    
}
