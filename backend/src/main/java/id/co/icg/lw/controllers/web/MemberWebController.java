package id.co.icg.lw.controllers.web;

import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.services.member.MemberService;
import id.co.icg.lw.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberWebController extends BaseController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getUser(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("title", "Member List");
        model.addAttribute("members", members);
        return "/member/index";

    }

}
