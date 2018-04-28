package id.co.icg.lw.controllers.web;

import id.co.icg.lw.domain.CurrentOffer;
import id.co.icg.lw.services.currentOffer.CreateCurrentOfferRequest;
import id.co.icg.lw.services.currentOffer.CurrentOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/current-offer")
public class CurrentOfferWebController extends BaseController {

    @Autowired
    private CurrentOfferService currentOfferService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCurrentOffer(Model model) {

        List<CurrentOffer> currentOffers = currentOfferService.findAll();
        model.addAttribute("currentOffers", currentOffers);
        model.addAttribute("title", "Current Offer List");
        return "current-offer/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCurrentOfferForm(Model model) {
        model.addAttribute("title", "Create Current Offer");
        return "current-offer/create_current_offer";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCurrentOffer(Model model, CreateCurrentOfferRequest request) {
        try{
            currentOfferService.createCurrentOffer(request);
            return "redirect:/current-offer";
        } catch (Exception e) {
            return "redirect: /current-offer/create";
        }

    }
}
