package id.co.icg.lw.services.merchant;

import id.co.icg.lw.services.message.MessageRequest;

import java.util.List;

public interface MerchantService {
    List<MerchantCategoryResponse> findAllOrderByMerchantCategoryName();
    MerchantResponse findOne(int merchantId);
}
