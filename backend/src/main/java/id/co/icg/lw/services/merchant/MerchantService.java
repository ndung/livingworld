package id.co.icg.lw.services.merchant;

import id.co.icg.lw.domain.merchant.Merchant;
import id.co.icg.lw.domain.merchant.MerchantCategory;

import java.util.List;

public interface MerchantService {
    List<MerchantCategoryResponse> findAllOrderByMerchantCategoryName();

    Merchant findOne(long merchantId);

    List<Merchant> findAllMerchant();

    List<MerchantCategory> findAllMerchantCategory();

    MerchantCategory findOne(String name);

    boolean createMerchant(Merchant merchant) throws Exception;

    boolean createCategory(String categoryId, String categoryName) throws Exception;



}
