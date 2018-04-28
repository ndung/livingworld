//package id.co.icg.lw.services.merchant;
//
//import id.co.icg.lw.domain.master.MerchantOfficeHour;
//import id.co.icg.lw.repositories.MerchantWorkingHoursRepository;
//import id.co.icg.lw.services.file.FileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MerchantServiceBean implements MerchantService {
//
//    @Autowired
//    private MerchantRepository merchantRepository;
//
//    @Autowired
//    private MerchantCategoryRepository merchantCategoryRepository;
//
//    @Autowired
//    private MerchantWorkingHoursRepository merchantWorkingHoursRepository;
//
//    @Autowired
//    private FileService fileService;
//
//    @Override
//    public List<MerchantCategoryResponse> findAllOrderByMerchantCategoryName() {
//        return merchantCategoryRepository.findAllOrderByMerchantCategoryName();
//    }
//
//    @Override
//    public id.co.icg.lw.domain.master.Merchant.Merchant findOne(long merchantId) {
//        return merchantRepository.findOne(merchantId);
//    }
//
//    @Override
//    public List<id.co.icg.lw.domain.master.Merchant.Merchant> findAllMerchant() {
//        return merchantRepository.findAll();
//    }
//
//    @Override
//    public List<MerchantCategory> findAllMerchantCategory() {
//        return merchantCategoryRepository.findAll();
//    }
//
//    @Override
//    public boolean createMerchant(CreateMerchantRequest request) {
//        id.co.icg.lw.domain.master.Merchant.Merchant merchant = new id.co.icg.lw.domain.master.Merchant.Merchant();
//
//        // upload image
//        if (request.getMerchantImage() != null) {
//            String imageUrl = fileService.upload(request.getMerchantImage());
//            merchant.setMerchantImage(imageUrl);
//        }
//
//        // upload logo
//        if (request.getMerchantLogo() != null) {
//            String logoUrl = fileService.upload(request.getMerchantLogo());
//            merchant.setMerchantLogo(logoUrl);
//        }
//
//
//        merchant.setMerchantName(request.getMerchantName());
//        merchant.setMerchantCategory(merchantCategoryRepository.findOne(request.getMerchantCategoryId()));
//
//        merchant.setDescription(request.getDescription());
//        merchant.setMerchantPhone(request.getMerchantPhone());
//
//        merchantRepository.saveAndFlush(merchant);
//
//        // set working hours
//        List<MerchantOfficeHour> hours = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            MerchantOfficeHour hour = new MerchantOfficeHour();
//            hour.setDay(i);
//            hour.setStartTime(request.getOpeningTime()[i]);
//            hour.setEndTime(request.getClosingTime()[i]);
//            hour.setMerchantId(merchant);
//            hours.add(hour);
//        }
//
//        merchantWorkingHoursRepository.save(hours);
//
//
//        return true;
//    }
//
//    @Override
//    public boolean updateMerchant(UpdateMerchantRequest request) {
//        return false;
//    }
//
//    @Override
//    public boolean createCategory(String categoryName) throws Exception {
//
//        MerchantCategory merchantCategory = merchantCategoryRepository.findByCategoryName(categoryName);
//        if (merchantCategory != null) {
//            throw new Exception("Category name is already used");
//        }
//        merchantCategory = new MerchantCategory();
//        merchantCategory.setMerchantCategoryName(categoryName);
//        merchantCategoryRepository.save(merchantCategory);
//        return true;
//    }
//
//    @Override
//    public boolean updateCategory(long merchantCategoryId, String categoryName) throws Exception {
//
//        MerchantCategory merchantCategory = merchantCategoryRepository.findOne(merchantCategoryId);
//        if (merchantCategory == null) {
//            throw new Exception("Category is not found");
//        }
//
//        merchantCategory.setMerchantCategoryName(categoryName);
//        merchantCategoryRepository.save(merchantCategory);
//        return true;
//    }
//}
