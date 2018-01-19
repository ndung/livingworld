package id.co.icg.lw.services.merchant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceBean implements MerchantService {

    @Override
    public List<MerchantCategoryResponse> findAllOrderByMerchantCategoryName() {
        return null;
    }

    @Override
    public MerchantResponse findOne(int merchantId) {
        return null;
    }
}
