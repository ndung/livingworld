package id.co.icg.lw.services.merchant;

import id.co.icg.lw.domain.merchant.Merchant;
import id.co.icg.lw.domain.merchant.MerchantCategory;

import java.util.List;

public interface MerchantService {
    List<MerchantCategoryResponse> findAllOrderByMerchantCategoryName();

    Merchant findOne(long merchantId);

    List<Merchant> findAllMerchant();

    List<MerchantCategory> findAllMerchantCategory();

    boolean createMerchant(CreateMerchantRequest request) throws Exception;

    boolean updateMerchant(UpdateMerchantRequest request);

    boolean createCategory(String categoryName) throws Exception;

    boolean updateCategory(long categoryId, String categoryName) throws Exception;


}
