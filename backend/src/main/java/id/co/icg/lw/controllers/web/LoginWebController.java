package id.co.icg.lw.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginWebController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "";
    }
}
