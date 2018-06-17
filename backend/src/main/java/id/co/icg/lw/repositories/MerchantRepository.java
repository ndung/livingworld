package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.Reward;
import id.co.icg.lw.domain.merchant.Merchant;
import id.co.icg.lw.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query("select m from Merchant m where m.merchantName like %:name%")
    List<Merchant> findByName(@Param("name") String name);
}
