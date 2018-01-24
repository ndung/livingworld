package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.merchant.MerchantCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface MerchantCategoryRepository extends JpaRepository<MerchantCategory, Long> {
    
}
