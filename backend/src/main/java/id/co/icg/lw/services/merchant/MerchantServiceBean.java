package id.co.icg.lw.services.merchant;

import id.co.icg.lw.domain.merchant.Merchant;
import id.co.icg.lw.domain.merchant.MerchantCategory;
import id.co.icg.lw.repositories.MerchantCategoryRepository;
import id.co.icg.lw.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceBean implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;

    @Override
    public List<MerchantCategoryResponse> findAllOrderByMerchantCategoryName() {
        return null;
    }

    @Override
    public MerchantResponse findOne(int merchantId) {
        return null;
    }

    @Override
    public List<Merchant> findAllMerchant() {
        return merchantRepository.findAll();
    }

    @Override
    public List<MerchantCategory> findAllMerchantCategory() {
        return merchantCategoryRepository.findAll();
    }
}
