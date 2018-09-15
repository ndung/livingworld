package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.merchant.MerchantCategory;
import id.co.icg.lw.services.merchant.MerchantCategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MerchantCategoryRepository extends JpaRepository<MerchantCategory, String> {

    @Query("select c from MerchantCategory c where c.merchantCategoryName = :categoryName")
    MerchantCategory findByCategoryName(@Param("categoryName") String categoryName);

    @Query("select new id.co.icg.lw.services.merchant.MerchantCategoryResponse(c) from MerchantCategory c order by c.merchantCategoryName")
    List<MerchantCategoryResponse> findAllOrderByMerchantCategoryName();
}
