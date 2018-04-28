//package id.co.icg.lw.controllers.web;
//
//import id.co.icg.lw.services.merchant.CreateMerchantRequest;
//import id.co.icg.lw.services.merchant.MerchantDetailResponse;
//import id.co.icg.lw.services.merchant.MerchantService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/merchant")
//public class MerchantWebController extends BaseController {
//
//    @Autowired
//    private MerchantService merchantService;
//
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String getMerchant(Model model) {
//        List<id.co.icg.lw.domain.master.Merchant.Merchant> merchants = merchantService.findAllMerchant();
//        model.addAttribute("merchants", merchants);
//        model.addAttribute("title", "Merchant List");
//        return "merchant/index";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public String getMerchantForm(Model model) {
//        model.addAttribute("title", "Create Merchant");
//        model.addAttribute("categories", merchantService.findAllMerchantCategory());
//        return "merchant/create_merchant";
//    }
//
//    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
//    public String getMerchantForm(Model model, @PathVariable("id") long id) {
//        MerchantDetailResponse merchantDetailResponse = new MerchantDetailResponse(merchantService.findOne(id));
//        model.addAttribute("title", "Merchant" + merchantDetailResponse.getMerchantName());
//        model.addAttribute("merchant", merchantDetailResponse);
//        model.addAttribute("categories", merchantService.findAllMerchantCategory());
//        return "merchant/detail_merchant";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String saveMerchantForm(Model model, CreateMerchantRequest createMerchantRequest) {
//        try{
//            merchantService.createMerchant(createMerchantRequest);
//            return "redirect:/merchant";
//        } catch (Exception e) {
//            return "redirect: /merchant/create";
//        }
//
//    }
//
//
//
//    @RequestMapping(value = "/category", method = RequestMethod.GET)
//    public String getMerchantCategory(Model model) {
//        List<MerchantCategory> categories = merchantService.findAllMerchantCategory();
//        model.addAttribute("categories", categories);
//        model.addAttribute("title", "Merchant Category List");
//        return "merchant/category";
//    }
//
//    @RequestMapping(value = "/category/create", method = RequestMethod.GET)
//    public String getMerchantCategoryForm(Model model) {
//        model.addAttribute("title", "Create Merchant Category");
//        return "merchant/create_merchant_category";
//    }
//
//    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
//    public String saveMerchantCategory(@Param("merchantCategoryName") String merchantCategoryName) {
//        try{
//            merchantService.createCategory(merchantCategoryName);
//            return "redirect:/merchant/category";
//        } catch (Exception e ){
//            return "merchant/category";
//        }
//
//    }
//
//    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
//    public String saveMerchantCategory(@Param("merchantCategoryId") long categoryId,
//                                       @Param("merchantCategoryName") String categoryName) {
//        try {
//            merchantService.updateCategory(categoryId, categoryName);
//            return "redirect:/merchant/category";
//        } catch (Exception e) {
//            return "merchant/category";
//        }
//
//    }
//}
