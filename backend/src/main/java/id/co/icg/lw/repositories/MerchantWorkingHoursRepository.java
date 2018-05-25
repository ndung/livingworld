package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.merchant.MerchantOfficeHour;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface MerchantWorkingHoursRepository extends JpaRepository<MerchantOfficeHour, Long> {

}
