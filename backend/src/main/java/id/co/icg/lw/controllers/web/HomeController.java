package id.co.icg.lw.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("redirect:/api/index.html" );
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ModelAndView home2(HttpServletRequest request) {
        return new ModelAndView("redirect:/api/index.html" );
    }

}
