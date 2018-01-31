package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(Application.API_PATH)
public class DocApiController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("redirect:/api/index.html" );
    }
}
