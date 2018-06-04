package id.co.icg.lw.controllers.web;

import id.co.icg.lw.domain.Message;

import id.co.icg.lw.services.message.MessageRequest;
import id.co.icg.lw.services.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageWebController extends BaseController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getUser(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        List<Message> messages = null;
        try {
            messages = messageService.getMessages(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("title", "Message");
        model.addAttribute("messages", messages);
        return "/message/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveMessage(Model model, MessageRequest request) {
        try{
            messageService.createMessage(request);
            return "redirect:/message";
        } catch (Exception e) {
            return "redirect: /message/create";
        }

    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getMerchantForm(Model model) {
        model.addAttribute("title", "Create Message");
        return "message/create_message";
    }


}
