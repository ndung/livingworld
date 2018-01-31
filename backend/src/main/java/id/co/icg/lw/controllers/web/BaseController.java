package id.co.icg.lw.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

    @Value("${basePath}")
    private String basePath;

    @ModelAttribute("basePath")
    public String getBasePath() {
        return basePath;
    }
}
