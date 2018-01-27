package id.co.icg.lw.controllers.web;

import id.co.icg.lw.domain.merchant.Merchant;
import id.co.icg.lw.domain.merchant.MerchantCategory;
import id.co.icg.lw.services.merchant.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/merchant")
public class MerchantWebController {

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getMerchant(Model model) {
        List<Merchant> merchants = merchantService.findAllMerchant();
        model.addAttribute("merchants", merchants);
        model.addAttribute("title", "Merchant List");
        return "merchant/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getMerchantForm(Model model) {
        return "merchant/create_merchant";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String getMerchantCategory(Model model) {
        List<MerchantCategory> categories = merchantService.findAllMerchantCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Merchant Category List");
        return "merchant/category";
    }
}
