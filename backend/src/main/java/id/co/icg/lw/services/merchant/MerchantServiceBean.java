package id.co.icg.lw.services.merchant;

import id.co.icg.lw.domain.merchant.Merchant;
import id.co.icg.lw.domain.merchant.MerchantCategory;
import id.co.icg.lw.domain.merchant.MerchantOfficeHour;
import id.co.icg.lw.repositories.MerchantCategoryRepository;
import id.co.icg.lw.repositories.MerchantRepository;
import id.co.icg.lw.repositories.MerchantWorkingHoursRepository;
import id.co.icg.lw.services.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantServiceBean implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;

    @Autowired
    private MerchantWorkingHoursRepository merchantWorkingHoursRepository;

    @Autowired
    private FileService fileService;

    @Override
    public List<MerchantCategoryResponse> findAllOrderByMerchantCategoryName() {
        return merchantCategoryRepository.findAllOrderByMerchantCategoryName();
    }

    @Override
    public Merchant findOne(long merchantId) {
        return merchantRepository.findOne(merchantId);
    }

    @Override
    public List<Merchant> findAllMerchant() {
        return merchantRepository.findAll();
    }

    @Override
    public List<MerchantCategory> findAllMerchantCategory() {
        return merchantCategoryRepository.findAll();
    }

    @Override
    public MerchantCategory findOne(String categoryName) {
        return merchantCategoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public boolean createMerchant(Merchant merchant) {

        //System.out.println("merchant:"+merchant);
        //merchant.setMerchantOfficeHourList(hours);
        try {
            merchantRepository.save(merchant);
        }catch(Exception ex){
            //ex.printStackTrace();
        }
        //merchantWorkingHoursRepository.save(hours);


        return true;
    }

    @Override
    public boolean createCategory(String categoryId, String categoryName) throws Exception {

        MerchantCategory merchantCategory = new MerchantCategory();
        merchantCategory.setMerchantCategoryId(categoryId);
        merchantCategory.setMerchantCategoryName(categoryName);
        merchantCategoryRepository.save(merchantCategory);
        return true;
    }
}
