package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.Redeem;
import id.co.icg.lw.domain.RedeemedReward;
import id.co.icg.lw.services.currentOffer.CurrentOfferRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RedeemRepository extends JpaRepository<Redeem, Long> {

    @Query("select r from Redeem r where r.member.cardNumber = :cardNumber order by r.createAt desc")
    List<Redeem> findRedeemsByCardNumber(@Param("cardNumber") String cardNumber);
}
