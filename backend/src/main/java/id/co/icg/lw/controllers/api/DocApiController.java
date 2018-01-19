package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Application.API_PATH)
public class DocApiController {

    @RequestMapping("")
    public String showDoc() {
        return "/api/index";
    }
}
