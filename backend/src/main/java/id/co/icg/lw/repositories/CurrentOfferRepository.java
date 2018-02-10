package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.CurrentOffer;
import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.services.currentOffer.CurrentOfferDetailRequest;
import id.co.icg.lw.services.currentOffer.CurrentOfferRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CurrentOfferRepository extends JpaRepository<CurrentOffer, Long> {

    @Query("select new id.co.icg.lw.services.currentOffer.CurrentOfferDetailRequest(co) from CurrentOffer co where co.currentOfferId = :id")
    CurrentOfferDetailRequest findById(@Param("id") long id);

    @Query("select new id.co.icg.lw.services.currentOffer.CurrentOfferRequest(co) from CurrentOffer co where " +
            "co.startDate < current_date() and co.endDate > current_date() order by co.createAt desc")
    List<CurrentOfferRequest> findAllCurrentOffer(Pageable pageable);
}
